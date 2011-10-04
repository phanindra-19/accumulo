package org.apache.accumulo.server.test.randomwalk.security;

import java.net.InetAddress;
import java.util.Properties;

import org.apache.accumulo.core.client.AccumuloException;
import org.apache.accumulo.core.client.AccumuloSecurityException;
import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.client.TableExistsException;
import org.apache.accumulo.core.client.TableNotFoundException;
import org.apache.accumulo.core.security.SystemPermission;
import org.apache.accumulo.core.security.TablePermission;
import org.apache.accumulo.core.security.thrift.SecurityErrorCode;
import org.apache.accumulo.server.test.randomwalk.State;
import org.apache.accumulo.server.test.randomwalk.Test;


public class AlterTable extends Test {

	@Override
	public void visit(State state, Properties props) throws Exception {
		Connector conn = SecurityHelper.getSystemConnector(state);

		String tableName = SecurityHelper.getTableName(state);

		boolean exists = SecurityHelper.getTableExists(state);
		boolean hasPermission = false;
		if (SecurityHelper.getSysPerm(state, SecurityHelper.getSysUserName(state), SystemPermission.ALTER_TABLE) || SecurityHelper.getTabPerm(state, SecurityHelper.getSysUserName(state), TablePermission.ALTER_TABLE))
			hasPermission = true;
		String newTableName = String.format("security_%s_%s_%d", InetAddress.getLocalHost().getHostName().replaceAll("[-.]", "_"), state.getPid(), System.currentTimeMillis());

		
		renameTable(conn, state, tableName, newTableName, hasPermission, exists);
	}
	
	public static void renameTable(Connector conn, State state, String oldName, String newName, boolean hasPermission, boolean tableExists) throws AccumuloSecurityException, AccumuloException, TableExistsException
	{
		try
		{
			conn.tableOperations().rename(oldName, newName);
		} catch (AccumuloSecurityException ae)
		{
			if (ae.getErrorCode().equals(SecurityErrorCode.PERMISSION_DENIED))
			{
				if (hasPermission)
					throw new AccumuloException("Got a security exception when I should have had permission.",ae);
				else
					return;
			}
			throw new AccumuloException("Got unexpected ae error code", ae);
		} catch (TableNotFoundException tnfe)
		{
			if (tableExists)
				throw new TableExistsException(null, oldName, "Got a TableNotFoundException but it should exist", tnfe);
			else
				return;
		}
		SecurityHelper.setTableName(state, newName);
		if (!hasPermission)
			throw new AccumuloException("Didn't get Security Exception when we should have");
	}
}
