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

// TODO: Auto-generated Javadoc
/**
 * The Class UUIDPCRGenarator.
 */
class UUIDPCRGenarator implements PCRGeneratable{

	/** The uuid. */
	String uuid;
	
	/* (non-Javadoc)
	 * @see com.wso2telco.core.pcrservice.PCRGeneratable#getPCR(com.wso2telco.core.pcrservice.model.RequestDTO)
	 */
	@Override
	public Returnable getPCR(RequestDTO dto) throws PCRException {
		// TODO Auto-generated method stub
		return new Returnable(){

			@Override
			public String getID() {
				// TODO Auto-generated method stub
				return uuid;
			}
			
		};
	}

}
