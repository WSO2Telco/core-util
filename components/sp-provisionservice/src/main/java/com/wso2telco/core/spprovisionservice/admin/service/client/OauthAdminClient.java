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
package com.wso2telco.core.spprovisionservice.admin.service.client;

import com.wso2telco.core.spprovisionservice.external.admin.service.dataTransform.TransformAdminServiceDto;
import com.wso2telco.core.spprovisionservice.sp.entity.AdminServiceConfig;
import com.wso2telco.core.spprovisionservice.sp.entity.AdminServiceDto;
import com.wso2telco.core.spprovisionservice.sp.exception.SpProvisionServiceException;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.identity.oauth.stub.OAuthAdminServiceException;
import org.wso2.carbon.identity.oauth.stub.OAuthAdminServiceStub;
import org.wso2.carbon.identity.oauth.stub.dto.OAuthConsumerAppDTO;

import java.rmi.RemoteException;

public class OauthAdminClient {

    private static final Logger log = LoggerFactory.getLogger(OauthAdminClient.class);
    private TransformAdminServiceDto transformAdminServiceDto = null;
    private OAuthAdminServiceStub oAuthAdminServiceStub = null;
    private ServiceClient client = null;
    private AdminServiceConfig adminServiceConfig = null;

    public OauthAdminClient(AdminServiceConfig adminServiceConfig) {
        this.adminServiceConfig = adminServiceConfig;
        createAndAuthenticateStub();
    }

    public void createAndAuthenticateStub() {
        try {
            oAuthAdminServiceStub = new OAuthAdminServiceStub(null, adminServiceConfig.getAdminServiceUrl());
            oAuthAdminServiceStub._getServiceClient().getOptions().setTimeOutInMilliSeconds(2 * 1000 * 60);
            client = oAuthAdminServiceStub._getServiceClient();

        } catch (AxisFault e) {
            log.error(e.getMessage());
        }
    }

    /*
     * Get Service Provider Application Details using consumerKey
     */
    public OAuthConsumerAppDTO getOauthApplicationDataByConsumerKey(String consumerKey)
            throws SpProvisionServiceException {

        OAuthConsumerAppDTO apps = null;
        OAuthConsumerAppDTO[] allAppDetails;

        authenticate(client);

        allAppDetails = getAllOAuthApplicationData();
        if (allAppDetails != null) {
            for (OAuthConsumerAppDTO oAuthConsumerAppDTO : allAppDetails) {
                if (oAuthConsumerAppDTO.getOauthConsumerKey().equals(consumerKey)) {
                    apps = oAuthConsumerAppDTO;
                    break;
                }
            }
        }

        return apps;
    }

    /*
     * Register OAuthApplication Data
     */
    public void registerOauthApplicationData(AdminServiceDto adminServiceDto) throws SpProvisionServiceException {

        transformAdminServiceDto = new TransformAdminServiceDto();
        OAuthConsumerAppDTO app = transformAdminServiceDto.transformToOAuthConsumerAppDto(adminServiceDto);
        authenticate(client);
        try {
            oAuthAdminServiceStub.registerOAuthApplicationData(app);
        } catch (RemoteException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (OAuthAdminServiceException e) {
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

    public void registerOauthApplicationData(OAuthConsumerAppDTO app, AdminServiceDto adminServiceDto)
            throws SpProvisionServiceException {
        authenticate(client);
        try {
            OAuthConsumerAppDTO appDto = transformAdminServiceDto.transformToOAuthConsumerAppDto(adminServiceDto);
            oAuthAdminServiceStub.registerOAuthApplicationData(appDto);
        } catch (RemoteException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (OAuthAdminServiceException e) {
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

    public void registerOauthApplicationData(OAuthConsumerAppDTO app) throws SpProvisionServiceException {
        authenticate(client);
        try {
            oAuthAdminServiceStub.registerOAuthApplicationData(app);
        } catch (RemoteException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (OAuthAdminServiceException e) {
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

    /*
     * removeOAuthApplicationData method
     */
    public void removeOauthApplicationData(String consumerKey) throws SpProvisionServiceException {

        authenticate(client);
        try {
            oAuthAdminServiceStub.removeOAuthApplicationData(consumerKey);
        } catch (RemoteException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (OAuthAdminServiceException e) {
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

    /*
     * Update OAuthConfigurations
     */
    public void updateOauthApplicationData(AdminServiceDto adminServiceDto) throws SpProvisionServiceException {

        OAuthConsumerAppDTO oAuthConsumerAppDTO = null;
        TransformAdminServiceDto transformAdminServiceDto = new TransformAdminServiceDto();
        oAuthConsumerAppDTO = transformAdminServiceDto.transformToOAuthConsumerAppDto(adminServiceDto);
        authenticate(client);

        try {
            oAuthAdminServiceStub.updateConsumerApplication(oAuthConsumerAppDTO);
        } catch (RemoteException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (OAuthAdminServiceException e) {
            throw new SpProvisionServiceException(e.getMessage());
        }
    }

    /*
     * Get all Oauth application details
     */
    private OAuthConsumerAppDTO[] getAllOAuthApplicationData() throws SpProvisionServiceException {

        OAuthConsumerAppDTO[] allAppDetails;
        authenticate(client);

        try {
            allAppDetails = oAuthAdminServiceStub.getAllOAuthApplicationData();
        } catch (RemoteException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (OAuthAdminServiceException e) {
            throw new SpProvisionServiceException(e.getMessage());
        }
        return allAppDetails;
    }

    public void authenticate(ServiceClient client) {
        Options option = client.getOptions();
        HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
        auth.setUsername(adminServiceConfig.getStubAccessUserName());
        auth.setPassword(adminServiceConfig.getStubAccessPassword());
        auth.setPreemptiveAuthentication(true);
        option.setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, auth);
        option.setManageSession(true);
    }
}
