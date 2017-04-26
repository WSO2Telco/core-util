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

import com.wso2telco.core.pcrservice.PCRGeneratable;
import com.wso2telco.core.pcrservice.Returnable;
import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;

// TODO: Auto-generated Javadoc

/**
 * The Class UUIDPCRGenarator.
 */
public class UUIDPCRGenarator implements PCRGeneratable {

    public UUIDPCRGenarator() {
    }

    private static Logger log = LoggerFactory.getLogger(UUIDPCRGenarator.class);
    public static final String ERROR_IN_RECEIVING_A_PCR = "error in receiving a PCR";
    public static final String ERROR_IN_RECIEVING_MSISDN = "error in receiving a MSISDN";

    /**
     * The uuid.
     */
    String uuid;

    /* (non-Javadoc)
     * @see com.wso2telco.core.pcrservice.PCRGeneratable#getPCR(com.wso2telco.core.pcrservice.model.RequestDTO)
     */
    @Override
    public Returnable getPCR(RequestDTO requestDTO) throws PCRException {


        try {
            UUIDPCRService uuidpcrService = new UUIDPCRService();
            uuid = uuidpcrService.getPcr(requestDTO);
        } catch (Exception e) {
            log.error(ERROR_IN_RECEIVING_A_PCR, e);
            throw new PCRException(ERROR_IN_RECEIVING_A_PCR);
        }

        return new Returnable() {
            @Override
            public String getID() {
                // TODO Auto-generated method stub
                return uuid;
            }
        };
    }



    @Override
    public Returnable getExistingPCR(RequestDTO dto) throws PCRException {
        try {
            UUIDPCRService uuidpcrService = new UUIDPCRService();
            uuid = uuidpcrService.getExistingPCR(dto);
        } catch (Exception e) {
            log.error(ERROR_IN_RECEIVING_A_PCR, e);
            throw new PCRException(ERROR_IN_RECEIVING_A_PCR);
        }

        return new Returnable() {
            @Override
            public String getID() {                
                return uuid;
            }
	    @Override
            public Boolean getAvailablity() {
                // TODO Auto-generated method stub
                return null;
            }
        };

    }

    @Override
    public Returnable getMsisdnByPcr(String sectorId, String pcr) throws PCRException {
        try {
            UUIDPCRService uuidpcrService = new UUIDPCRService();
            uuid = uuidpcrService.getMsisdnByPcr(sectorId,pcr);
        } catch (Exception e) {
            log.error(ERROR_IN_RECIEVING_MSISDN, e);
            throw new PCRException(ERROR_IN_RECIEVING_MSISDN);
        }

        return new Returnable() {
            @Override
            public String getID() {
                return uuid;
            }
        };
    }

    Boolean appAvailable = false;

    @Override
    public Returnable isAppAvailableFor(String sector, String appId) throws PCRException {

        try {
            UUIDPCRService uuidpcrService = new UUIDPCRService();
            appAvailable = uuidpcrService.isAppAvailableFor(sector, appId);
        } catch (Exception e) {
            log.error("error in app availability check", e);
            throw new PCRException("error in app availability check");
        }

        return new Returnable() {
            @Override
            public Boolean getAvailablity() {
                // TODO Auto-generated method stub
                return appAvailable;
            }

            @Override
            public String getID() {
                return null;
            }
        };
    }

}
