/*******************************************************************************
 * Copyright  (c) 2015-2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * 
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.wso2telco.core.pcrservice.dao;

import java.sql.SQLException;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.wso2telco.core.pcrservice.model.UserDTO;

/**
 * The Class UserHandler.
 */
public abstract class UserHandler {

	/**
	 * User persister.
	 *
	 * @return the persistable user
	 */
	@CreateSqlObject
	abstract PersistableUser  UserPersister();
	
	/**
	 * Creates the new user.
	 *
	 * @param UserDTO the user DTO
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public int createNewUser(UserDTO UserDTO) throws SQLException{		
		int newUserDid=0;		
		newUserDid = UserPersister().insertUser(UserDTO.getUserId());
		return newUserDid;		
	}
	
	/**
	 * Check user exists.
	 *
	 * @param UserDTO the user DTO
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean checkUserExists(UserDTO UserDTO) throws SQLException{
		boolean exists = false;
		exists = UserPersister().checkUserExists(UserDTO.getUserId());
		return exists;
	}
	
	/**
	 * Check user active.
	 *
	 * @param UserDTO the user DTO
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean checkUserActive(UserDTO UserDTO) throws SQLException{
		boolean active = false;
		active = UserPersister().checkUserActive(UserDTO.getUserId());
		return active;
	}
	
	/**
	 * Sets the user active.
	 *
	 * @param UserDTO the new user active
	 * @throws SQLException the SQL exception
	 */
	public void setUserActive(UserDTO UserDTO) throws SQLException{		
		UserPersister().setUserActive(UserDTO.getUserId());		
	}
	
	/**
	 * Sets the user inactive.
	 *
	 * @param UserDTO the new user inactive
	 * @throws SQLException the SQL exception
	 */
	public void setUserInactive(UserDTO UserDTO) throws SQLException{		
		UserPersister().setUserInactive(UserDTO.getUserId());		
	}
}
