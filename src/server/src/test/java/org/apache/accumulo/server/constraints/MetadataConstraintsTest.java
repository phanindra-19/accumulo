package org.apache.accumulo.server.constraints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.accumulo.core.Constants;
import org.apache.accumulo.core.conf.AccumuloConfiguration;
import org.apache.accumulo.core.data.Mutation;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.util.ColumnFQ;
import org.apache.accumulo.server.constraints.MetadataConstraints;
import org.apache.hadoop.io.Text;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;



public class MetadataConstraintsTest {

	@Test
	public void testCheck() {
	    Logger.getLogger(AccumuloConfiguration.class).setLevel(Level.ERROR);
		Mutation m = new Mutation(new Text("0;foo"));
		ColumnFQ.put(m, Constants.METADATA_PREV_ROW_COLUMN, new Value("1foo".getBytes()));
		
		MetadataConstraints mc = new MetadataConstraints();
		 
		List<Short> violations = mc.check(null, m);
		
		assertNotNull(violations);
		assertEquals(1, violations.size());
		assertEquals(Short.valueOf((short)3), violations.get(0));
		
		
		m = new Mutation(new Text("0:foo"));
		ColumnFQ.put(m, Constants.METADATA_PREV_ROW_COLUMN, new Value("1poo".getBytes()));
			 
		violations = mc.check(null, m);
		
		assertNotNull(violations);
		assertEquals(1, violations.size());
		assertEquals(Short.valueOf((short)4), violations.get(0));
		
		
		m = new Mutation(new Text("0;foo"));
		m.put(new Text("bad_column_name"), new Text(""), new Value("e".getBytes()));
			 
		violations = mc.check(null, m);
		
		assertNotNull(violations);
		assertEquals(1, violations.size());
		assertEquals(Short.valueOf((short)2), violations.get(0));
		
		
		m = new Mutation(new Text("!!<"));
		ColumnFQ.put(m, Constants.METADATA_PREV_ROW_COLUMN, new Value("1poo".getBytes()));
			 
		violations = mc.check(null, m);
		
		assertNotNull(violations);
		assertEquals(2, violations.size());
		assertEquals(Short.valueOf((short)4),violations.get(0));
		assertEquals(Short.valueOf((short)5),violations.get(1));
		
		
		
		m = new Mutation(new Text("0;foo"));
		ColumnFQ.put(m, Constants.METADATA_PREV_ROW_COLUMN, new Value("".getBytes()));
			 
		violations = mc.check(null, m);
		
		assertNotNull(violations);
		assertEquals(1, violations.size());
		assertEquals(Short.valueOf((short)6), violations.get(0));
		
		
		
		
		m = new Mutation(new Text("0;foo"));
		ColumnFQ.put(m, Constants.METADATA_PREV_ROW_COLUMN, new Value("bar".getBytes()));
			 
		violations = mc.check(null, m);
		
		assertEquals(null, violations);
		
		
		m = new Mutation(new Text("!0<"));
		ColumnFQ.put(m, Constants.METADATA_PREV_ROW_COLUMN, new Value("bar".getBytes()));
			 
		violations = mc.check(null, m);
		
		assertEquals(null, violations);
		
		
		
		m = new Mutation(new Text("!1<"));
		ColumnFQ.put(m, Constants.METADATA_PREV_ROW_COLUMN, new Value("bar".getBytes()));
			 
		violations = mc.check(null, m);
		
		assertNotNull(violations);
		assertEquals(1, violations.size());
		assertEquals(Short.valueOf((short)4), violations.get(0));
		
	}

}
