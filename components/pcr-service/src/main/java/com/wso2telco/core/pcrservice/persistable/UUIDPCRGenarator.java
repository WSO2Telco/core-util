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
import com.wso2telco.core.pcrservice.dao.KeyValueBasedPcrDAOImpl;
import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;

import java.util.List;
import java.util.UUID;

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
	public Returnable getPCR(RequestDTO requestDTO) throws PCRException {

        KeyValueBasedPcrDAOImpl keyValueBasedPcrDAO = new KeyValueBasedPcrDAOImpl();
	    if(validateParameters(requestDTO)) {
	        log.error("Mandatory parameters are empty");
	        throw new PCRException("Mandatory parameters are empty");
        }
	    //check whether there is an existing pcr for the given UserId, SectorId, AppId combination (RequestDTO)
		String pcr = keyValueBasedPcrDAO.getExistingPCR(requestDTO);
		//if there is an existing pcr for the given RequestDTO
        if(pcr != null){
            uuid = pcr;
        }else{
        	//Otherwise check whether there is an existing application/s for the given userId, SectorId combination 
            List<String> applicationIdList = keyValueBasedPcrDAO.getAppIdListForUserSectorCombination(requestDTO.getUserId(), requestDTO.getSectorId());
            //if there is no application for the given userId, sector id combination, create and persist new pcr 
            if(applicationIdList.isEmpty()){
            	uuid = createAndPersistNewPcr(requestDTO);
            }else{
            	//if there is/are application/s for the given userId, sector id combination
            	//check whether the applicationId exists in the service provider map
            	if(keyValueBasedPcrDAO.checkApplicationExists(requestDTO.getSectorId(), requestDTO.getAppId())){

            		//if the given applicationId exists in the service provider map check whether the app is related
            		if(keyValueBasedPcrDAO.checkIsRelated(requestDTO.getSectorId(), requestDTO.getAppId())){
            			boolean relatedAppExists = false;
            			String applicationId = null;
                    	for (String appId : applicationIdList) {
        					relatedAppExists = keyValueBasedPcrDAO.checkIsRelated(requestDTO.getSectorId(), appId);
        					if(relatedAppExists){
        						applicationId = appId;         						
        						break;
        					}
        				}         
                    	if(relatedAppExists){
                    		RequestDTO newRequestDTO = new RequestDTO(requestDTO.getUserId(), applicationId, requestDTO.getSectorId());
                    		uuid = keyValueBasedPcrDAO.getExistingPCR(newRequestDTO);   
                    		createAndPersistNewPcr(requestDTO, uuid);
                    	}else{
                    		uuid = createAndPersistNewPcr(requestDTO);
                    	}
            		}else{
            			//if the app is not related return a new pcr and persist
            			uuid = createAndPersistNewPcr(requestDTO);
            		}
            	}else{
            		//if the given app id is not in the service provider map, update the map and return new pcr
            		updateServiceProviderMap(requestDTO.getAppId());
            		uuid = createAndPersistNewPcr(requestDTO);
            	}	
            }
        }

		return new Returnable(){
			@Override
			public String getID() {
				// TODO Auto-generated method stub
				return uuid;
			}
		};
	}

	private void updateServiceProviderMap(String appId) {
		// TODO Auto-generated method stub
		
	}

	private String createAndPersistNewPcr(RequestDTO requestDTO) throws PCRException {
		KeyValueBasedPcrDAOImpl keyValueBasedPcrDAO = new KeyValueBasedPcrDAOImpl();
		UUID uuidPcr = UUID.randomUUID();
    	keyValueBasedPcrDAO.createNewPcrEntry(requestDTO, uuidPcr.toString());
		return uuidPcr.toString();
	}
	
	private void createAndPersistNewPcr(RequestDTO requestDTO, String pcr) throws PCRException {
		KeyValueBasedPcrDAOImpl keyValueBasedPcrDAO = new KeyValueBasedPcrDAOImpl();
    	keyValueBasedPcrDAO.createNewPcrEntry(requestDTO, pcr);		
	}

	private boolean validateParameters(RequestDTO dto) throws PCRException {
        return dto.getUserId() == null || dto.getAppId() == null || dto.getSectorId() == null ||
                dto.getUserId().equals("") || dto.getAppId().equals("") || dto.getSectorId().equals("");
	}
	
	

}
