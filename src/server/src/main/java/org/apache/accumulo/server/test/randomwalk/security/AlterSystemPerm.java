package org.apache.accumulo.server.test.randomwalk.security;

import java.util.Properties;
import java.util.Random;

import org.apache.accumulo.core.client.AccumuloException;
import org.apache.accumulo.core.client.AccumuloSecurityException;
import org.apache.accumulo.core.client.Connector;
import org.apache.accumulo.core.security.SystemPermission;
import org.apache.accumulo.server.test.randomwalk.State;
import org.apache.accumulo.server.test.randomwalk.Test;


public class AlterSystemPerm extends Test {

	@Override
	public void visit(State state, Properties props) throws Exception {
		Connector conn = state.getConnector();

		String action = props.getProperty("task", "toggle");
		String perm = props.getProperty("perm", "random");

		String targetUser = SecurityHelper.getSysUserName(state);

		SystemPermission sysPerm;
		if (perm.equals("random"))
		{
			Random r = new Random();
			int i = r.nextInt(SystemPermission.values().length);
			sysPerm = SystemPermission.values()[i];
		} else
			sysPerm = SystemPermission.valueOf(perm);

		boolean hasPerm = SecurityHelper.getSysPerm(state, SecurityHelper.getSysUserName(state), sysPerm); 

		//toggle
		if (!"take".equals(action) && !"give".equals(action))
		{
			if (hasPerm != conn.securityOperations().hasSystemPermission(targetUser, sysPerm))
				throw new AccumuloException("Test framework and accumulo are out of sync!");
			if (hasPerm)
				action="take";
			else
				action="give";
		}

		if ("take".equals(action))
		{
			try
			{
				conn.securityOperations().revokeSystemPermission(targetUser, sysPerm);
			} catch (AccumuloSecurityException ae)
			{
				switch (ae.getErrorCode())
				{
				case GRANT_INVALID:
					if (sysPerm.equals(SystemPermission.GRANT))
						return;
				case PERMISSION_DENIED:
					throw new AccumuloException("Test user doesn't have root",ae);
				case USER_DOESNT_EXIST:
					throw new AccumuloException("System user doesn't exist and they SHOULD.",  ae);
				default:
					throw new AccumuloException("Got unexpected exception",ae);
				}
			}
			SecurityHelper.setSysPerm(state, SecurityHelper.getSysUserName(state), sysPerm, false);
		} else if ("give".equals(action))
		{
			try
			{
				conn.securityOperations().grantSystemPermission(targetUser, sysPerm);
			} catch (AccumuloSecurityException ae)
			{
				switch (ae.getErrorCode())
				{
				case GRANT_INVALID:
					if (sysPerm.equals(SystemPermission.GRANT))
						return;
				case PERMISSION_DENIED:
					throw new AccumuloException("Test user doesn't have root",ae);
				case USER_DOESNT_EXIST:
					throw new AccumuloException("System user doesn't exist and they SHOULD.",  ae);
				default:
					throw new AccumuloException("Got unexpected exception",ae);
				}
			}
			SecurityHelper.setSysPerm(state, SecurityHelper.getSysUserName(state), sysPerm, true);
		} 
	}


}
