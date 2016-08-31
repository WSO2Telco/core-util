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
package com.wso2telco.core.pcrservice.persistable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wso2telco.core.pcrservice.dao.ApplicationDAO;
import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.ApplicationDTO;

/**
 * The Class AppService.
 */
public class ApplicationService {
	
	private static Logger log = LoggerFactory.getLogger(ApplicationService.class);

	/**
	 * Gets the application.
	 *
	 * @param appID the app ID
	 * @return the application
	 * @throws PCRException the PCR exception
	 */
	public ApplicationDTO getApplication(final String appID) throws PCRException{
		ApplicationDTO applicationDTO = new ApplicationDTO();
		
		applicationDTO.setAppId(appID);
		try {
			ApplicationDAO applicationDAO = new ApplicationDAO();
			if(applicationDAO.checkApplicationExists(applicationDTO)){
				return applicationDTO;
			}else{
				log.error("No Application found");
				throw new PCRException("No Application found");
			}
		}catch(Exception e){
			log.error("getApplication() failed ", e);
			throw new PCRException("getApplication() failed");
		}		
	}
	
	/**
	 * Creates the application.
	 *
	 * @param appID the app ID
	 * @throws PCRException the PCR exception
	 */
	public void createApplication(final String appID) throws PCRException{
		
		ApplicationDTO applicationDTO = new ApplicationDTO();
		
		applicationDTO.setAppId(appID);
		try {
			ApplicationDAO applicationDAO = new ApplicationDAO();
			if(applicationDAO.checkApplicationExists(applicationDTO)){
				log.error("Application already exists");
				throw new PCRException("Application already exists");				
			}else{
				applicationDAO.saveNewApplication(applicationDTO);
			}
		}catch(Exception e){
			log.error("createApplication() failed ", e);
			throw new PCRException("createApplication() failed");
		}		
	}
}
