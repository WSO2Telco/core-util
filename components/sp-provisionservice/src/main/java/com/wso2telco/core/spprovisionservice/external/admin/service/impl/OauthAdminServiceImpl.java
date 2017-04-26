/*******************************************************************************
 * Copyright  (c) 2015-2017, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
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
package com.wso2telco.core.spprovisionservice.external.admin.service.impl;

import com.wso2telco.core.spprovisionservice.admin.service.client.OauthAdminClient;
import com.wso2telco.core.spprovisionservice.external.admin.service.OauthAdminService;
import com.wso2telco.core.spprovisionservice.external.admin.service.dataTransform.TransformAdminServiceDto;
import com.wso2telco.core.spprovisionservice.sp.entity.AdminServiceDto;
import com.wso2telco.core.spprovisionservice.sp.entity.SpProvisionConfig;
import com.wso2telco.core.spprovisionservice.sp.exception.SpProvisionServiceException;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.oauth.stub.dto.OAuthConsumerAppDTO;

public class OauthAdminServiceImpl implements OauthAdminService {

    private static Log log = LogFactory.getLog(OauthAdminServiceImpl.class);
    private OauthAdminClient oauthAdminServiceClient = null;

    private SpProvisionConfig spProvisionConfig = null;

    public void initialize(SpProvisionConfig spProvisionConfig) {
        this.spProvisionConfig = spProvisionConfig;
        System.setProperty("javax.net.ssl.trustStore", System.getProperty("carbon.home") +
                File.separator + "repository/resources/security/client-truststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
    }

    @Override
    public void removeOAuthApplicationData(String consumerKey) throws SpProvisionServiceException {

        oauthAdminServiceClient = new OauthAdminClient(this.spProvisionConfig.getAdminServiceConfig());
        try {
            oauthAdminServiceClient.removeOauthApplicationData(consumerKey);
        } catch (SpProvisionServiceException e) {
            log.error("Error occurred in remove Oauth Application " + e.getMessage());
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

    @Override
    public boolean rebuildOAuthApplicationData(AdminServiceDto adminServiceDto, OAuthConsumerAppDTO oAuthConsumerAppDTO)
            throws SpProvisionServiceException {
        boolean isRebuiltSuccess = false;
        if (adminServiceDto != null) {
            oauthAdminServiceClient = new OauthAdminClient(this.spProvisionConfig.getAdminServiceConfig());
            try {
                TransformAdminServiceDto transformAdminServiceDto = new TransformAdminServiceDto();
                AdminServiceDto adminSerDto = transformAdminServiceDto.transformToOAuthConsumerAppDto(adminServiceDto,
                        oAuthConsumerAppDTO);
                OAuthConsumerAppDTO appDto = transformAdminServiceDto.transformToOAuthConsumerAppDto(adminSerDto);
                oauthAdminServiceClient.removeOauthApplicationData(adminServiceDto.getOauthConsumerKey());
                oauthAdminServiceClient.registerOauthApplicationData(appDto);
            } catch (SpProvisionServiceException e) {
                log.error("Error occurred in registering the oAuth data");
                throw new SpProvisionServiceException(e.getMessage());
            }
        } else {
            log.info("Admin Service Dto is null.Can't proceed Service Provider provisioning");
        }
        return isRebuiltSuccess;
    }

    @Override
    public OAuthConsumerAppDTO getOAuthApplicationData(AdminServiceDto adminServiceDto)
            throws SpProvisionServiceException {

        OAuthConsumerAppDTO oAuthConsumerAppDTO = null;
        oauthAdminServiceClient = new OauthAdminClient(this.spProvisionConfig.getAdminServiceConfig());
        try {
            if (adminServiceDto != null) {
                oAuthConsumerAppDTO = oauthAdminServiceClient
                        .getOauthApplicationDataByConsumerKey(adminServiceDto.getOauthConsumerKey());
            } else {
                log.info("Admin Service Dto is null.Can't proceed Service Provider provisioning");
                throw new SpProvisionServiceException(
                        "Admin Service Dto is null.Can't proceed Service Provider provisioning");
            }

        } catch (SpProvisionServiceException e) {
            log.error("Error occurred in registering the oAuth data");
            throw new SpProvisionServiceException(e.getMessage());
        }
        return oAuthConsumerAppDTO;
    }

    @Override
    public boolean isCredentailsEquals(AdminServiceDto adminServiceDto, OAuthConsumerAppDTO oAuthConsumerAppDTO)
            throws SpProvisionServiceException {
        boolean isSecretIdentical = false;
        if (adminServiceDto.getOauthConsumerSecret() != null) {
            if (oAuthConsumerAppDTO.getOauthConsumerSecret().equals(adminServiceDto.getOauthConsumerSecret())) {
                isSecretIdentical = true;
            }
        }
        return isSecretIdentical;
    }

    @Override
    public boolean registerOAuthApplicationData(AdminServiceDto adminServiceDto) throws SpProvisionServiceException {

        boolean success = true;
        boolean failure = false;
        boolean status;

        if (adminServiceDto != null && adminServiceDto.getApplicationName()!= null && !adminServiceDto
                .getApplicationName().isEmpty() && adminServiceDto.getCallbackUrl() != null && !adminServiceDto
                .getCallbackUrl().isEmpty() && adminServiceDto.getOauthConsumerKey() != null && !adminServiceDto
                .getOauthConsumerKey().isEmpty()) {
            oauthAdminServiceClient = new OauthAdminClient(this.spProvisionConfig.getAdminServiceConfig());
            try {
                oauthAdminServiceClient.registerOauthApplicationData(adminServiceDto);
                status = success;
            } catch (SpProvisionServiceException e) {
                log.error("Error occurred in registering the oAuth data");
                throw new SpProvisionServiceException(e.getMessage());
            }
        } else {
            log.info("Admin Service Dto is null.Can't proceed Service Provider provisioning");
            status = failure;
        }
        return status;
    }

    @Override
    public AdminServiceDto getOauthServiceProviderData(String consumerKey) throws SpProvisionServiceException {

        OAuthConsumerAppDTO oAuthConsumerAppDTO;
        oauthAdminServiceClient = new OauthAdminClient(this.spProvisionConfig.getAdminServiceConfig());
        AdminServiceDto adminServiceDto;
        TransformAdminServiceDto transformAdminServiceDto = new TransformAdminServiceDto();

        try {
            oAuthConsumerAppDTO = oauthAdminServiceClient.getOauthApplicationDataByConsumerKey(consumerKey);
            adminServiceDto = transformAdminServiceDto.transformToAdminServiceDto(oAuthConsumerAppDTO);
        } catch (SpProvisionServiceException e) {
            log.error("Error while getting Oauth details of the service provider for the given consumer key");
            throw new SpProvisionServiceException(e.getMessage());
        }
        return adminServiceDto;
    }
}
