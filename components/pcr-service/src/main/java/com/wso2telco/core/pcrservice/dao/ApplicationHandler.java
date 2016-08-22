/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wso2telco.core.pcrservice.dao;

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
	 */
	public int createNewApplication(ApplicationDTO applicationDTO){		
		int newApplicationDid=0;		
		newApplicationDid = applicationPersister().insert(applicationDTO.getAppId());
		return newApplicationDid;		
	}
	
	/**
	 * Check application exists.
	 *
	 * @param applicationDTO the application DTO
	 * @return true, if successful
	 */
	public boolean checkApplicationExists(ApplicationDTO applicationDTO){
		boolean exists = false;
		exists = applicationPersister().select(applicationDTO.getAppId());
		return exists;
	}
}
