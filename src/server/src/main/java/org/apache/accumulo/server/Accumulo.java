package org.apache.accumulo.server;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.accumulo.core.Constants;
import org.apache.accumulo.core.file.FileUtil;
import org.apache.accumulo.core.trace.DistributedTrace;
import org.apache.accumulo.core.util.CachedConfiguration;
import org.apache.accumulo.core.util.UtilWaitThread;
import org.apache.accumulo.core.util.Version;
import org.apache.accumulo.server.client.HdfsZooInstance;
import org.apache.accumulo.server.conf.ServerConfiguration;
import org.apache.accumulo.server.trace.TraceFileSystem;
import org.apache.accumulo.server.zookeeper.ZooReaderWriter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.FSConstants.SafeModeAction;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.zookeeper.KeeperException;


public class Accumulo {

	private static final Logger log = Logger.getLogger(Accumulo.class);
	private static Integer dataVersion = null;

	public static synchronized int getAccumuloPersistentVersion() {
		if (dataVersion != null) return dataVersion;

		Configuration conf = CachedConfiguration.getInstance();
		try {
			FileSystem fs = TraceFileSystem.wrap(FileUtil.getFileSystem(conf, ServerConfiguration.getSiteConfiguration()));

			FileStatus[] files = fs.listStatus(ServerConstants.getDataVersionLocation());
			if(files == null || files.length == 0) {
				dataVersion = -1; //assume it is 0.5 or earlier
			}
			else {
				dataVersion = Integer.parseInt(files[0].getPath().getName());
			}
			return dataVersion;
		}catch(IOException e){
			throw new RuntimeException("Unable to read accumulo version: an error occurred.", e);
		}

	}
	
	public static void enableTracing(String address, String application) {
	    try {
	        DistributedTrace.enable(HdfsZooInstance.getInstance(), ZooReaderWriter.getInstance(), application, address);
	    } catch (Exception ex) {
	        log.error("creating remote sink for trace spans", ex);
	    }
	}
	
	public static void init(String application) throws UnknownHostException {

	    System.setProperty("org.apache.accumulo.core.application", application);
	    
	    if(System.getenv("ACCUMULO_LOG_DIR") != null)
			System.setProperty("org.apache.accumulo.core.dir.log", System.getenv("ACCUMULO_LOG_DIR"));
		else
			System.setProperty("org.apache.accumulo.core.dir.log", System.getenv("ACCUMULO_HOME")+"/logs/");
		
		String localhost = InetAddress.getLocalHost().getHostName();
		System.setProperty("org.apache.accumulo.core.ip.localhost.hostname",localhost);
		
		if (System.getenv("ACCUMULO_LOG_HOST") != null)
            System.setProperty("org.apache.accumulo.core.host.log", System.getenv("ACCUMULO_LOG_HOST"));
        else
            System.setProperty("org.apache.accumulo.core.host.log", localhost);
		
		// Use a specific log config, if it exists
		String logConfig = String.format("%s/conf/%s_logger.xml", System.getenv("ACCUMULO_HOME"), application);
		if (!new File(logConfig).exists()) {
		    // otherwise, use the generic config
		    logConfig = String.format("%s/conf/generic_logger.xml", System.getenv("ACCUMULO_HOME"));
		}
		// Turn off messages about not being able to reach the remote logger... we protect against that.
        LogLog.setQuietMode(true);

        // Configure logging
		DOMConfigurator.configure(logConfig);
		
		log.info(application + " starting");
        log.info("Instance "+HdfsZooInstance.getInstance().getInstanceID());
        log.info("Data Version " + Accumulo.getAccumuloPersistentVersion());
        Accumulo.waitForZookeeperAndHdfs();

		int dataVersion = Accumulo.getAccumuloPersistentVersion();
		Version codeVersion = new Version(Constants.VERSION);
		if (dataVersion != Constants.DATA_VERSION){
			throw new RuntimeException("This version of accumulo ("+codeVersion+
					") is not compatible with files stored using data version " + 
					dataVersion);
		}
		
		TreeMap<String, String> sortedProps = new TreeMap<String, String>();
		for (Entry<String, String> entry : ServerConfiguration.getSystemConfiguration())
			sortedProps.put(entry.getKey(), entry.getValue());

		for (Entry<String, String> entry : sortedProps.entrySet())
			log.info(entry.getKey()+" = "+entry.getValue());
	}
	

    public static InetAddress getLocalAddress(String[] args) throws UnknownHostException {
        InetAddress result = InetAddress.getLocalHost();
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-a") || args[i].equals("--address")) {
                result = InetAddress.getByName(args[i+1]);
                log.debug("Local address is: " + args[i+1] + " (" + result.toString() + ")");
                break;
            }
        }
        return result;
    }
    
    public static void waitForZookeeperAndHdfs() {
        log.info("Attempting to talk to zookeeper");
        while (true) {
            try {
                ZooReaderWriter.getInstance().getChildren(Constants.ZROOT);
                break;
            } catch (InterruptedException e) {
                // ignored
            } catch (KeeperException ex) {
                log.info("Waiting for accumulo to be initialized");
                UtilWaitThread.sleep(1000);
            }
        }
        log.info("Zookeeper connected and initialized, attemping to talk to HDFS");
        long sleep = 1000;
        while (true) {
            try {
                DistributedFileSystem dfs = (DistributedFileSystem)FileSystem.get(CachedConfiguration.getInstance());
                if (!dfs.setSafeMode(SafeModeAction.SAFEMODE_GET))
                    break;
                log.warn("Waiting for the NameNode to leave safemode");
                sleep = 1000;
            } catch (IOException ex) {
                log.warn("Unable to connect to HDFS");
            }
            log.info("Sleeping "  + sleep / 1000. + " seconds");
            UtilWaitThread.sleep(sleep);
            sleep = Math.min(60*1000, sleep * 2);
        }
        log.info("Connected to HDFS");
    }
}
