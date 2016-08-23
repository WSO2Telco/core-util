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

import com.wso2telco.core.pcrservice.model.ApplicationDTO;


// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationHandler.
 */
public abstract class ApplicationHandler {

	/**
	 * Application persister.
	 *
	 * @return the persistable application
	 */
	@CreateSqlObject
	abstract PersistableApplication  applicationPersister();
	
	/**
	 * Creates the new application.
	 *
	 * @param applicationDTO the application DTO
	 * @return the int
	 * @throws SQLException the SQL exception
	 */
	public int createNewApplication(ApplicationDTO applicationDTO) throws SQLException{		
		int newApplicationDid=0;		
		newApplicationDid = applicationPersister().insertApplication(applicationDTO.getAppId());
		return newApplicationDid;		
	}
	
	/**
	 * Check application exists.
	 *
	 * @param applicationDTO the application DTO
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean checkApplicationExists(ApplicationDTO applicationDTO) throws SQLException{
		boolean exists = false;
		exists = applicationPersister().checkApplicationExists(applicationDTO.getAppId());
		return exists;
	}
	
	/**
	 * Check application active.
	 *
	 * @param applicationDTO the application DTO
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public boolean checkApplicationActive(ApplicationDTO applicationDTO) throws SQLException{
		boolean active = false;
		active = applicationPersister().checkApplicationActive(applicationDTO.getAppId());
		return active;
	}
	
	/**
	 * Sets the application active.
	 *
	 * @param applicationDTO the new application active
	 * @throws SQLException the SQL exception
	 */
	public void setApplicationActive(ApplicationDTO applicationDTO) throws SQLException{		
		applicationPersister().setApplicationActive(applicationDTO.getAppId());		
	}
	
	/**
	 * Sets the application inactive.
	 *
	 * @param applicationDTO the new application inactive
	 * @throws SQLException the SQL exception
	 */
	public void setApplicationInactive(ApplicationDTO applicationDTO) throws SQLException{		
		applicationPersister().setApplicationInactive(applicationDTO.getAppId());		
	}
}
