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

import com.wso2telco.core.pcrservice.PCRGeneratable;
import com.wso2telco.core.pcrservice.Returnable;
import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class UUIDPCRGenarator.
 */
class UUIDPCRGenarator implements PCRGeneratable{

	private static Logger log = LoggerFactory.getLogger(UUIDPCRGenarator.class);

	/** The uuid. */
	String uuid;
	
	/* (non-Javadoc)
	 * @see com.wso2telco.core.pcrservice.PCRGeneratable#getPCR(com.wso2telco.core.pcrservice.model.RequestDTO)
	 */
	@Override
	public Returnable getPCR(RequestDTO dto) throws PCRException {
		
		validateParameters(dto);
		
		
		return new Returnable(){

			@Override
			public String getID() {
				// TODO Auto-generated method stub
				return uuid;
			}
			
		};
	}

	private void validateParameters(RequestDTO dto) throws PCRException {

		if(dto.getAppId() == null){
			log.error("App id is null");
			throw new PCRException("App id is null");
		}else if(dto.getSectorId() == null){
			log.error("Sector id is null");
			throw new PCRException("sector id is null");
		}else if(dto.getUserId() == null){
			log.error("User id is null");
			throw new PCRException("User id is null");
		}
	}

}
