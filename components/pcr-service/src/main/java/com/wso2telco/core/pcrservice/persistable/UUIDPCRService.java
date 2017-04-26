package com.wso2telco.core.pcrservice.persistable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.identity.oauth.stub.dto.OAuthConsumerAppDTO;

import com.wso2telco.core.pcrservice.dao.impl.KeyValueBasedPcrDAOImpl;
import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;
import com.wso2telco.core.pcrservice.oauth.OAuthApplicationData;
import com.wso2telco.core.pcrservice.util.SectorUtil;

public class UUIDPCRService {

    /**
     * The uuid.
     */
    String uuid;

    private static Logger log = LoggerFactory.getLogger(UUIDPCRService.class);
    private static boolean DEBUG = log.isDebugEnabled();
    OAuthApplicationData authApplicationData = null;

    public UUIDPCRService() {
        authApplicationData = new OAuthApplicationData();
    }

    public boolean isAppAvailableFor(String sector, String appId) throws PCRException {
        KeyValueBasedPcrDAOImpl keyValueBasedPcrDAOImpl = new KeyValueBasedPcrDAOImpl();
        return keyValueBasedPcrDAOImpl.checkApplicationExists(sector, appId);
    }

    public String getPcr(RequestDTO requestDTO) throws PCRException {

        KeyValueBasedPcrDAOImpl keyValueBasedPcrDAO = new KeyValueBasedPcrDAOImpl();
        if (DEBUG) {
            log.debug("PCR service : userID : " + requestDTO.getUserId());
            log.debug("PCR service : appID : " + requestDTO.getAppId());
            log.debug("PCR service : sectorID : " + requestDTO.getSectorId());
        }
        if (validateParameters(requestDTO)) {
            log.error("Mandatory parameters are empty");
            throw new PCRException("Mandatory parameters are empty");
        }
        // check whether there is an existing pcr for the given UserId,
        // SectorId, AppId combination (RequestDTO)
        String pcr = keyValueBasedPcrDAO.getExistingPCR(requestDTO);
        // if there is an existing pcr for the given RequestDTO
        if (pcr != null) {
            if (DEBUG)
                log.debug("PCR service : Existing PCR found");
            uuid = pcr;
        } else {
            if (DEBUG) {
                log.debug("PCR service : There is no Exisiting PCR, Creating New PCR");
                log.debug(
                        "PCR service : check whether there is an existing application, for the given UserId, SectorId");
            }
            // Otherwise check whether there is an existing application/s for
            // the given userId, SectorId combination
            List<String> applicationIdList = keyValueBasedPcrDAO
                    .getAppIdListForUserSectorCombination(requestDTO.getUserId(), requestDTO.getSectorId());
            // if there is no application for the given userId, sector id
            // combination, create and persist new pcr
            if (applicationIdList.isEmpty()) {
                log.debug("PCR service : No Existing app for the given UserID, SectorID");
                updateServiceProviderMap(requestDTO.getAppId());
                uuid = createAndPersistNewPcr(requestDTO);
            } else {
                // if there is/are application/s for the given userId, sector id
                // combination
                // check whether the applicationId exists in the service
                // provider map
                if (DEBUG) {
                    log.debug("PCR service : Existing application found");
                    log.debug("PCR service : Check application exists in the SP map");
                }
                if (!keyValueBasedPcrDAO.checkApplicationExists(requestDTO.getSectorId(), requestDTO.getAppId())) {
                    updateServiceProviderMap(requestDTO.getAppId());
                }

                if (DEBUG) {
                    log.debug("PCR service : Aplication found in the SP map");
                    log.debug("PCR service : Check whether the app is related");
                }
                // if the given applicationId exists in the service provider
                // map check whether the app is related
                if (keyValueBasedPcrDAO.checkIsRelated(requestDTO.getSectorId(), requestDTO.getAppId())) {
                    boolean relatedAppExists = false;
                    String applicationId = null;
                    for (String appId : applicationIdList) {
                        relatedAppExists = keyValueBasedPcrDAO.checkIsRelated(requestDTO.getSectorId(), appId);
                        if (relatedAppExists) {
                            applicationId = appId;
                            break;
                        }
                    }
                    if (relatedAppExists) {
                        RequestDTO newRequestDTO = new RequestDTO(requestDTO.getUserId(), applicationId,
                                requestDTO.getSectorId());
                        uuid = keyValueBasedPcrDAO.getExistingPCR(newRequestDTO);
                        createAndPersistNewPcr(requestDTO, uuid);
                    } else {
                        uuid = createAndPersistNewPcr(requestDTO);
                    }
                } else {
                    // if the app is not related return a new pcr and
                    // persist
                    uuid = createAndPersistNewPcr(requestDTO);
                }

            }
        }

        return uuid;
    }

    private void updateServiceProviderMap(String appId) throws PCRException {

        if (DEBUG)
            log.debug("Updating Service Provider Map");
        try {
            OAuthConsumerAppDTO apps = authApplicationData.getApplicationData(appId);
            if (DEBUG)
                log.debug("Application List recieved");
            if (apps == null) {
                log.error("Application data not found - updateServiceProviderMap");
                throw new PCRException("Null Application list - updateServiceProviderMap");
            }

            String callbackUrl = apps.getCallbackUrl();
            String sector = SectorUtil.getSectorIdFromUrl(callbackUrl);
            KeyValueBasedPcrDAOImpl keyValueBasedPcrDAOImpl = new KeyValueBasedPcrDAOImpl();
            keyValueBasedPcrDAOImpl.createNewSPEntry(sector, appId, true);

        } catch (Exception e) {
            log.error("error in retrieving application data - updateServiceProviderMap", e);
            throw new PCRException("error in retrieving application data - updateServiceProviderMap");
        }
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
        return dto.getUserId() == null || dto.getAppId() == null || dto.getSectorId() == null
                || dto.getUserId().equals("") || dto.getAppId().equals("") || dto.getSectorId().equals("");
    }

}