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

import com.wso2telco.core.spprovisionservice.admin.config.AdministrationServiceConfig;
import com.wso2telco.core.spprovisionservice.sp.exception.SpProvisionServiceException;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.identity.application.mgt.stub.IdentityApplicationManagementServiceStub;

import java.net.MalformedURLException;
import java.net.URL;

public class ApplicationManagementServiceClientStubFactory extends BasePoolableObjectFactory {

    private static final Logger log = LoggerFactory.getLogger(ApplicationManagementServiceClientStubFactory.class);
    private AdministrationServiceConfig config;
    private HttpClient httpClient;

    public ApplicationManagementServiceClientStubFactory() {
        this.config = AdministrationServiceConfig.getInstance();
        this.httpClient = createHttpClient();
    }

    /**
     * This creates a OAuth2TokenValidationServiceStub object to the pool.
     *
     * @return an OAuthValidationStub object
     * @throws SpProvisionServiceException throws custom exception
     */
    @Override
    public Object makeObject() throws SpProvisionServiceException {
        return this.generateStub();
    }

    /**
     * This is used to clean up the OAuth validation stub and releases to the object pool.
     *
     * @param o object that needs to be released.
     * @throws Exception throws when failed to release to the pool
     */
    @Override
    public void passivateObject(Object o) throws Exception {
        if (o instanceof IdentityApplicationManagementServiceStub) {
            IdentityApplicationManagementServiceStub stub = (IdentityApplicationManagementServiceStub) o;
            stub._getServiceClient().cleanupTransport();
        }
    }

    /**
     * This is used to create a stub which will be triggered through object pool factory, which will create an
     * instance of it.
     *
     * @return OAuth2TokenValidationServiceStub stub that is used to call an external service.
     * @throws SpProvisionServiceException will be thrown when initialization failed.
     */
    public IdentityApplicationManagementServiceStub generateStub() throws SpProvisionServiceException {
        IdentityApplicationManagementServiceStub stub;
        try {
            URL hostURL = new URL(config.getApplicationManagementHostUrl());
            stub = new IdentityApplicationManagementServiceStub(null, hostURL.toString());
            ServiceClient client = stub._getServiceClient();
            authenticate(client);

        } catch (AxisFault e) {
            log.error("Error occurred while creating the OAuth2TokenValidationServiceStub.");
            throw new SpProvisionServiceException(e.getMessage());
        } catch (MalformedURLException e) {
            log.error("Error occurred while creating the OAuth2TokenValidationServiceStub.");
            throw new SpProvisionServiceException(e.getMessage());
        } catch (Exception e) {
            log.error("Error occurred while creating the OAuth2TokenValidationServiceStub.");
            throw new SpProvisionServiceException(e.getMessage());
        }
        return stub;
    }

    /**
     * This created httpclient pool that can be used to connect to external entity. This connection can be configured
     * via broker.xml by setting up the required http connection parameters.
     *
     * @return an instance of HttpClient that is configured with MultiThreadedHttpConnectionManager
     */
    private HttpClient createHttpClient() {
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setDefaultMaxConnectionsPerHost(config.getMaximumHttpConnectionPerHost());
        params.setMaxTotalConnections(config.getMaximumTotalHttpConnection());
        HttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.setParams(params);
        return new HttpClient(connectionManager);
    }

    public void authenticate(ServiceClient client) {
        Options option = client.getOptions();
        HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
        auth.setUsername(config.getUsername());
        auth.setPassword(config.getPassword());
        auth.setPreemptiveAuthentication(true);
        option.setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, auth);
        option.setManageSession(true);
    }
}
