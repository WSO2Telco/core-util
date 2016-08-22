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
package com.wso2telco.core.pcrservice.persistable;

import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.ApplicationDTO;
import com.wso2telco.core.pcrservice.model.SectorDTO;
import com.wso2telco.core.pcrservice.model.UserAssignmentDTO;
import com.wso2telco.core.pcrservice.model.UserDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class UserManageService.
 */
public class UserManageService {
	
	/**
	 * Gets the user.
	 *
	 * @param userID the user ID
	 * @return the user
	 * @throws PCRException the PCR exception
	 */
	public UserDTO getUser(final String userID) throws PCRException{
		return null;
	}
	
	/**
	 * Creates the app.
	 *
	 * @param appID the app ID
	 * @return the user DTO
	 * @throws PCRException the PCR exception
	 */
	public UserDTO createApp(final UserDTO appID) throws PCRException{
		return null;
	}
	
	/**
	 * Creates the user assignment.
	 *
	 * @param user the user
	 * @param appDTO the app DTO
	 * @param sector the sector
	 * @return the user assignment DTO
	 * @throws PCRException the PCR exception
	 */
	public UserAssignmentDTO createUserAssignment(final UserDTO user,final ApplicationDTO appDTO, final SectorDTO sector) throws PCRException{
		return null;
	}
	
	
	
}
