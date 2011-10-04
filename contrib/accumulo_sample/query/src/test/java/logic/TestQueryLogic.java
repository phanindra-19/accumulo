package logic;

import ingest.WikipediaConfiguration;
import ingest.WikipediaMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RawLocalFileSystem;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import parser.RangeCalculator;
import reader.AggregatingRecordReader;
import sample.Document;
import sample.Field;
import sample.Results;
import org.apache.accumulo.core.client.BatchWriter;
import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.MutationsRejectedException;
import org.apache.accumulo.core.client.Scanner;
import org.apache.accumulo.core.client.mock.MockInstance;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Mutation;
import org.apache.accumulo.core.data.Range;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.security.Authorizations;

public class TestQueryLogic {

	private static final String METADATA_TABLE_NAME = "wikiMetadata";
	
	private static final String TABLE_NAME = "wiki";
	
	private static final String INDEX_TABLE_NAME = "wikiIndex";
	
	private static final String RINDEX_TABLE_NAME = "wikiReverseIndex";
	
	private class MockAccumuloRecordWriter extends RecordWriter<Text, Mutation> {
		@Override
		public void write(Text key, Mutation value) throws IOException, InterruptedException {
			try {
				writerMap.get(key).addMutation(value);
			} catch (MutationsRejectedException e) {
				throw new IOException("Error adding mutation", e);
			}
		}

		@Override
		public void close(TaskAttemptContext context) throws IOException, InterruptedException {
				try {
					for (BatchWriter w : writerMap.values()) {
						w.flush();
						w.close();
					}
				} catch (MutationsRejectedException e) {
					throw new IOException("Error closing Batch Writer", e);
				}
		}
		
	}

	private Connector c = null;
	private Configuration conf = new Configuration();
	private HashMap<Text,BatchWriter> writerMap = new HashMap<Text,BatchWriter>();
	private QueryLogic table = null;

	@Before
	public void setup() throws Exception {
		
		Logger.getLogger(AbstractQueryLogic.class).setLevel(Level.DEBUG);
		Logger.getLogger(QueryLogic.class).setLevel(Level.DEBUG);
		Logger.getLogger(RangeCalculator.class).setLevel(Level.DEBUG);
		
		conf.set(AggregatingRecordReader.START_TOKEN, "<page>");
		conf.set(AggregatingRecordReader.END_TOKEN, "</page>");
		conf.set(WikipediaConfiguration.TABLE_NAME, TABLE_NAME);
		conf.set(WikipediaConfiguration.NUM_PARTITIONS, "1");

		
		MockInstance i = new MockInstance();
        c = i.getConnector("root", "pass");
		c.tableOperations().delete(METADATA_TABLE_NAME);
		c.tableOperations().delete(TABLE_NAME);
		c.tableOperations().delete(INDEX_TABLE_NAME);
		c.tableOperations().delete(RINDEX_TABLE_NAME);
        c.tableOperations().create(METADATA_TABLE_NAME);
        c.tableOperations().create(TABLE_NAME);
		c.tableOperations().create(INDEX_TABLE_NAME);
		c.tableOperations().create(RINDEX_TABLE_NAME);

        writerMap.put(new Text(METADATA_TABLE_NAME), c.createBatchWriter(METADATA_TABLE_NAME, 1000L, 1000L, 1));
        writerMap.put(new Text(TABLE_NAME), c.createBatchWriter(TABLE_NAME, 1000L, 1000L, 1));
        writerMap.put(new Text(INDEX_TABLE_NAME), c.createBatchWriter(INDEX_TABLE_NAME, 1000L, 1000L, 1));
        writerMap.put(new Text(RINDEX_TABLE_NAME), c.createBatchWriter(RINDEX_TABLE_NAME, 1000L, 1000L, 1));

        TaskAttemptID id = new TaskAttemptID();
		TaskAttemptContext context = new TaskAttemptContext(conf, id);

		RawLocalFileSystem fs = new RawLocalFileSystem();
		fs.setConf(conf);

		URL url = ClassLoader.getSystemResource("enwiki-20110901-001.xml");
		Assert.assertNotNull(url);
		File data = new File(url.toURI());
		Path tmpFile = new Path(data.getAbsolutePath());
        
		//Setup the Mapper
		InputSplit split = new FileSplit(tmpFile, 0, fs.pathToFile(tmpFile).length(), null);
		AggregatingRecordReader rr = new AggregatingRecordReader();
		Path ocPath = new Path(tmpFile, "oc");
		OutputCommitter oc = new FileOutputCommitter(ocPath, context);
		fs.deleteOnExit(ocPath);
		StandaloneStatusReporter sr = new StandaloneStatusReporter();
		rr.initialize(split, context);
		MockAccumuloRecordWriter rw = new MockAccumuloRecordWriter();
		WikipediaMapper mapper = new WikipediaMapper();
		
		//Load data into Mock Accumulo
		Mapper<LongWritable, Text, Text, Mutation>.Context con = mapper.new Context(conf, id, rr, rw, oc, sr, split);
		mapper.run(con);
		
		//Flush and close record writers.
		rw.close(context);
		
		table = new QueryLogic();
		table.setMetadataTableName(METADATA_TABLE_NAME);
		table.setTableName(TABLE_NAME);
		table.setIndexTableName(INDEX_TABLE_NAME);
		table.setReverseIndexTableName(RINDEX_TABLE_NAME);
		table.setUseReadAheadIterator(false);
		table.setNumPartitions(1);
		
	}
	
	private void debugQuery(String tableName) throws Exception {
		Scanner s = c.createScanner(tableName, new Authorizations());
		Range r = new Range();
		s.setRange(r);
		for (Entry<Key,Value> entry : s)
			System.out.println(entry.getKey().toString() +" " + entry.getValue().toString());
	}

	@Test
	public void testTitle() {
		List<String> auths = new ArrayList<String>();
		auths.add("enwiki");
		Results results = table.runQuery(c, auths, "TITLE == 'afghanistanhistory'", null, null, null);
		for (Document doc : results.getResults()) {
			System.out.println("id: " + doc.getId());
			for (Field field : doc.getFields())
				System.out.println(field.getFieldName() + " -> " + field.getFieldValue());
		}
	}

}
