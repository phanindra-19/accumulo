package org.apache.accumulo.server.master.tableOps;

import org.apache.accumulo.core.Constants;
import org.apache.accumulo.core.client.Instance;
import org.apache.accumulo.core.client.impl.Tables;
import org.apache.accumulo.core.client.impl.thrift.TableOperation;
import org.apache.accumulo.core.client.impl.thrift.TableOperationExceptionType;
import org.apache.accumulo.core.client.impl.thrift.ThriftTableOperationException;
import org.apache.accumulo.core.zookeeper.ZooUtil;
import org.apache.accumulo.server.client.HdfsZooInstance;
import org.apache.accumulo.server.fate.Repo;
import org.apache.accumulo.server.master.Master;
import org.apache.accumulo.server.zookeeper.ZooReaderWriter;
import org.apache.accumulo.server.zookeeper.ZooReaderWriter.Mutator;
import org.apache.log4j.Logger;


public class RenameTable extends MasterRepo {

	private static final long serialVersionUID = 1L;
	private String tableId;
	private String oldTableName;
	private String newTableName;

	@Override
	public long isReady(long tid, Master environment) throws Exception {
		return Utils.reserveTable(tableId, tid, true, true, TableOperation.RENAME);
	}
	
	public RenameTable(String tableId, String oldTableName, String newTableName){
		this.tableId = tableId;
		this.oldTableName = oldTableName;
		this.newTableName = newTableName;
	}
	
	@Override
	public Repo<Master> call(long tid, Master env) throws Exception {
		
		Instance instance = HdfsZooInstance.getInstance();

		
		ZooReaderWriter zoo = ZooReaderWriter.getInstance();
		Utils.tableNameLock.lock();
		 try {
			 Utils.checkTableDoesNotExist(instance, newTableName, tableId, TableOperation.RENAME);
			 
             final String tap = ZooUtil.getRoot(instance) + Constants.ZTABLES + "/" + tableId
                     + Constants.ZTABLE_NAME;

             zoo.mutate(tap, null, null, new Mutator() {
                public byte[] mutate(byte[] current) throws Exception {
                    final String currentName = new String(current);
                    if(currentName.equals(newTableName))
                        return null; //assume in this case the operation is running again, so we are done
                    if (!currentName.equals(oldTableName)) {
                        throw new ThriftTableOperationException(null,
                                                                oldTableName,
                                                                TableOperation.RENAME,
                                                                TableOperationExceptionType.NOTFOUND,
                                                                "Name changed while processing");
                    }
                    return newTableName.getBytes();
                }
             });
             Tables.clearCache(instance);
         }finally{
        	 Utils.tableNameLock.unlock();
        	 Utils.unreserveTable(tableId, tid, true);
         }
         
         Logger.getLogger(RenameTable.class).debug("Renamed table "+tableId+" "+oldTableName+" "+newTableName);
         
		return null;
	}

	@Override
	public void undo(long tid, Master env) throws Exception {
		Utils.unreserveTable(tableId, tid, true);
	}

	

}
