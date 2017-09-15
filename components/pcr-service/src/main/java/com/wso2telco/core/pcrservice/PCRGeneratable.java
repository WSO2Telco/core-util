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
package com.wso2telco.core.pcrservice;

import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;

// TODO: Auto-generated Javadoc

/**
 * The Interface PCRGeneratable.
 */
public interface PCRGeneratable {

    /**
     * Gets the pcr.
     *
     * @param dto the dto
     * @return the pcr
     * @throws PCRException the PCR exception
     */
    Returnable getPCR(RequestDTO dto) throws PCRException;

    /**
     * Gets the pcr.
     *
     * @param dto the dto
     * @return the pcr
     * @throws PCRException the PCR exception
     */
    Returnable getExistingPCR(RequestDTO dto) throws PCRException;

    /**
     * Gets the msisdn.
     *
     * @param sectorId the sectorId
     * @return the msisdn
     * @throws PCRException the PCR exception
     */
    Returnable getMsisdnByPcr(String sectorId, String pcr) throws PCRException;
}
