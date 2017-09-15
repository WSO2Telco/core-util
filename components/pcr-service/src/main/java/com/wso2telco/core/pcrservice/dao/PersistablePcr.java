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

import java.util.List;

import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;

// TODO: Auto-generated Javadoc

/**
 * The Interface PersistablePcr.
 */
public interface PersistablePcr {

    public void createNewPcrEntry(RequestDTO requestDTO, String pcr) throws PCRException;

    /**
     * Gets the existing PCR.
     *
     * @param requestDTO the user assignment DTO
     * @return the existing PCR
     * @throws PCRException the PCR exception
     */
    public String getExistingPCR(RequestDTO requestDTO) throws PCRException;

    /**
     * Check user sector combination.
     *
     * @param userId   the user id
     * @param sectorId the sector id
     * @return true, if successful
     * @throws PCRException the PCR exception
     */
    public List<String> getAppIdListForUserSectorCombination(String userId, String sectorId) throws PCRException;

    /**
     * Gets the application ids.
     *
     * @param sectorId the sector id
     * @return the application ids
     * @throws PCRException the PCR exception
     */
    public List<String> getApplicationIdList(String sectorId) throws PCRException;

    /**
     * Check is related.
     *
     * @param sectorId the sector id
     * @param appId    the app id
     * @return true, if successful
     * @throws PCRException the PCR exception
     */
    public boolean checkIsRelated(String sectorId, String appId) throws PCRException;

    /**
     * Creates the new SP entry.
     *
     * @param sectorId  the sector id
     * @param appId     the app id
     * @param isRelated the is related
     * @throws PCRException the PCR exception
     */
    public void createNewSPEntry(String sectorId, String appId, boolean isRelated) throws PCRException;

    /**
     * Check application exists.
     *
     * @param sectorId the sector id
     * @param appId    the app id
     * @return true, if successful
     * @throws PCRException the PCR exception
     */
    public boolean checkApplicationExists(String sectorId, String appId) throws PCRException;

    /**
     * Creates the new msisdn entry
     *
     * @param sectorId the sector id
     * @param userId    the user id
     * @param pcr the pcr
     * @throws PCRException the PCR exception
     */
    public void createNewPCRMSISDNEntry(String userId,String sectorId,String pcr) throws PCRException;

    public String getMSISDNbyPcr(String sectorId,String pcr) throws PCRException;
}
