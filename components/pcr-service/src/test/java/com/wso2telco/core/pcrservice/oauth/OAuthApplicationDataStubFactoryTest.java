package com.wso2telco.core.pcrservice.oauth;

import com.wso2telco.core.pcrservice.conf.AdminServiceConfig;
import com.wso2telco.core.pcrservice.exception.PCRException;
import org.apache.axis2.Constants;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.commons.httpclient.HttpClient;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.oauth.stub.OAuthAdminServiceStub;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Copyright (c) 2016, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 * <p>
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class OAuthApplicationDataStubFactoryTest {

    private AdminServiceConfig adminServiceConfigMock;

    @BeforeMethod
    public void setUp() {
        adminServiceConfigMock = Mockito.mock(AdminServiceConfig.class);
        when(adminServiceConfigMock.getHostUrl()).thenReturn("http://test-utl.com");
        when(adminServiceConfigMock.getUsername()).thenReturn("test-username");
        when(adminServiceConfigMock.getPassword()).thenReturn("test-password");
        when(adminServiceConfigMock.getMaximumHttpConnectionPerHost()).thenReturn(1);
        when(adminServiceConfigMock.getMaximumTotalHttpConnection()).thenReturn(2);
    }

    @AfterMethod
    public void tearDown() {
        adminServiceConfigMock = null;
    }

    @Test
    public void testMakeObject_shouldReturnStub_always() throws PCRException {
        Object object = new OAuthApplicationDataStubFactory(adminServiceConfigMock).makeObject();
        Assertions.assertThat(object).isInstanceOf(OAuthAdminServiceStub.class);

        OAuthAdminServiceStub oAuthAdminServiceStub = (OAuthAdminServiceStub) object;
        ServiceClient serviceClient = oAuthAdminServiceStub._getServiceClient();
        Object property = serviceClient.getServiceContext().getConfigurationContext().getProperty(HTTPConstants.CACHED_HTTP_CLIENT);
        Assertions.assertThat(property).isInstanceOf(HttpClient.class);

        HttpClient httpClient = (HttpClient) property;
        Assertions.assertThat(httpClient.getHttpConnectionManager().getParams().getDefaultMaxConnectionsPerHost()).isEqualTo(1);
        Assertions.assertThat(httpClient.getHttpConnectionManager().getParams().getMaxTotalConnections()).isEqualTo(2);

        Assertions.assertThat(serviceClient.getOptions().getProperty(HTTPConstants.REUSE_HTTP_CLIENT)).isEqualTo(Constants.VALUE_TRUE);

        Object authProperty = serviceClient.getOptions().getProperty(HTTPConstants.AUTHENTICATE);
        Assertions.assertThat(authProperty).isInstanceOf(HttpTransportProperties.Authenticator.class);

        HttpTransportProperties.Authenticator authenticator = (HttpTransportProperties.Authenticator) authProperty;
        Assertions.assertThat(authenticator.getPreemptiveAuthentication()).isTrue();
        Assertions.assertThat(authenticator.getUsername()).isEqualTo("test-username");
        Assertions.assertThat(authenticator.getPassword()).isEqualTo("test-password");
    }

    @Test
    public void testPassivateObject_shouldCleanupTransport_forOAuthAdminServiceStubObjects() throws Exception {
        ServiceClient serviceClientMock = Mockito.mock(ServiceClient.class);
        OAuthAdminServiceStub oAuthAdminServiceStubMock = Mockito.mock(OAuthAdminServiceStub.class);
        when(oAuthAdminServiceStubMock._getServiceClient()).thenReturn(serviceClientMock);
        new OAuthApplicationDataStubFactory(adminServiceConfigMock).passivateObject(oAuthAdminServiceStubMock);
        verify(serviceClientMock, times(1)).cleanupTransport();
    }
}