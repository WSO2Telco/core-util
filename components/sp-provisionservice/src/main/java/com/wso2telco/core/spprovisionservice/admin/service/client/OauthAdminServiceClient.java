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
import com.wso2telco.core.spprovisionservice.sp.entity.AdminServiceDto;
import com.wso2telco.core.spprovisionservice.sp.exception.SpProvisionServiceException;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.identity.oauth.stub.OAuthAdminServiceException;
import org.wso2.carbon.identity.oauth.stub.OAuthAdminServiceStub;
import org.wso2.carbon.identity.oauth.stub.dto.OAuthConsumerAppDTO;

import java.rmi.RemoteException;


public class OauthAdminServiceClient {
    private static final Logger log = LoggerFactory.getLogger(OauthAdminServiceClient.class);
    private static String cookie;
    private GenericObjectPool stubs;
    private static boolean DEBUG = log.isDebugEnabled();
    private TransformAdminServiceDto transformAdminServiceDto = null;
    private OauthAdminServiceClientStubFactory oauthAdminServiceClientStubFactory = null;
    private OAuthAdminServiceStub oAuthAdminServiceStub = null;

    public OauthAdminServiceClient() throws SpProvisionServiceException {
        stubs = new GenericObjectPool(new OauthAdminServiceClientStubFactory());
        generateAndAuthenticateStub();
    }

    public void generateAndAuthenticateStub() {
        Object stub = null;
        try {
            stub = this.stubs.borrowObject();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (stub != null) {
            try {
                oAuthAdminServiceStub = new OAuthAdminServiceStub();
            } catch (AxisFault axisFault) {
                axisFault.printStackTrace();
            }

            if (cookie != null) {
                oAuthAdminServiceStub._getServiceClient().getOptions().setProperty(HTTPConstants.COOKIE_STRING,
                        cookie);
            }
            ServiceContext serviceContext = oAuthAdminServiceStub._getServiceClient()
                    .getLastOperationContext().getServiceContext();
            cookie = (String) serviceContext.getProperty(HTTPConstants.COOKIE_STRING);
        }
    }

    /*
    * Get Service Provider Application Details
    * */
    public OAuthConsumerAppDTO getOauthApplicationDataByAppName(String appName) throws SpProvisionServiceException {

        OAuthConsumerAppDTO apps;
        try {
            apps = oAuthAdminServiceStub.getOAuthApplicationDataByAppName(appName);
        } catch (RemoteException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (OAuthAdminServiceException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (NullPointerException e) {
            return null;
        }
        return apps;
    }

    /*
    * Register OAuthApplication Data
    * */
    public void registerOauthApplicationData(AdminServiceDto adminServiceDto) throws SpProvisionServiceException {

        transformAdminServiceDto = new TransformAdminServiceDto();
        OAuthConsumerAppDTO app = transformAdminServiceDto.transformToOAuthConsumerAppDto(adminServiceDto);
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
    * */
    public void removeOauthApplicationData(String consumerKey) throws SpProvisionServiceException {

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
    * */
    public void updateOauthApplicationData(AdminServiceDto adminServiceDto) throws SpProvisionServiceException {

        OAuthConsumerAppDTO oAuthConsumerAppDTO;
        TransformAdminServiceDto transformAdminServiceDto = new TransformAdminServiceDto();
        oAuthConsumerAppDTO = transformAdminServiceDto.transformToOAuthConsumerAppDto(adminServiceDto);

        try {
            oAuthAdminServiceStub.updateConsumerApplication(oAuthConsumerAppDTO);
        } catch (RemoteException e) {
            throw new SpProvisionServiceException(e.getMessage());
        } catch (OAuthAdminServiceException e) {
            throw new SpProvisionServiceException(e.getMessage());
        }
    }
}
