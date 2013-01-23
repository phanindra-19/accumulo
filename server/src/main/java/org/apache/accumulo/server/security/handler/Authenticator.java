/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.accumulo.server.security.handler;

import java.util.Set;

import org.apache.accumulo.core.client.AccumuloSecurityException;
import org.apache.accumulo.core.security.thrift.ThriftSecurityException;
import org.apache.accumulo.core.security.tokens.AccumuloToken;
import org.apache.accumulo.core.security.tokens.InstanceTokenWrapper;

/**
 * This interface is used for the system which will be used for authenticating a user. If the implementation does not support configuration through Accumulo, it
 * should throw an AccumuloSecurityException with the error code UNSUPPORTED_OPERATION
 */

public interface Authenticator {
  
  public void initialize(String instanceId);

  public boolean validSecurityHandlers(Authorizor auth, PermissionHandler pm);

  public void initializeSecurity(InstanceTokenWrapper credentials, AccumuloToken<?,?> at) throws AccumuloSecurityException, ThriftSecurityException;

  public boolean authenticateUser(AccumuloToken<?,?> token) throws AccumuloSecurityException;
  
  public Set<String> listUsers() throws AccumuloSecurityException;
  
  public void createUser(AccumuloToken<?,?> user) throws AccumuloSecurityException;
  
  public void dropUser(String user) throws AccumuloSecurityException;
  
  public void changePassword(AccumuloToken<?,?> user) throws AccumuloSecurityException;
  
  public boolean userExists(String user);

  public String getTokenClassName();
}
