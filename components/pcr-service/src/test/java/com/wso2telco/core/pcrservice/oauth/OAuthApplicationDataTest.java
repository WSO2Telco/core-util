package com.wso2telco.core.pcrservice.oauth;

import java.rmi.RemoteException;

import com.wso2telco.core.pcrservice.exception.PCRException;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.OperationContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.oauth.stub.OAuthAdminServiceException;
import org.wso2.carbon.identity.oauth.stub.OAuthAdminServiceStub;
import org.wso2.carbon.identity.oauth.stub.dto.OAuthConsumerAppDTO;
import static org.fest.reflect.core.Reflection.field;
import static org.mockito.Matchers.anyString;
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
public class OAuthApplicationDataTest {

    private GenericObjectPool genericObjectPoolMock;
    private OAuthConsumerAppDTO oAuthConsumerAppDTOMock;
    private OAuthAdminServiceStub oAuthAdminServiceStubMock;
    private Options optionsMock;

    @BeforeMethod
    public void setUp() throws Exception {
        oAuthConsumerAppDTOMock = Mockito.mock(OAuthConsumerAppDTO.class);

        ServiceContext serviceContextMock = Mockito.mock(ServiceContext.class);
        when(serviceContextMock.getProperty(HTTPConstants.COOKIE_STRING)).thenReturn("mocked-cookie");

        OperationContext operationContextMock = Mockito.mock(OperationContext.class);
        when(operationContextMock.getServiceContext()).thenReturn(serviceContextMock);

        optionsMock = Mockito.mock(Options.class);

        ServiceClient serviceClientMock = Mockito.mock(ServiceClient.class);
        when(serviceClientMock.getLastOperationContext()).thenReturn(operationContextMock);
        when(serviceClientMock.getOptions()).thenReturn(optionsMock);

        oAuthAdminServiceStubMock = Mockito.mock(OAuthAdminServiceStub.class);
        when(oAuthAdminServiceStubMock.getOAuthApplicationData(anyString())).thenReturn(oAuthConsumerAppDTOMock);
        when(oAuthAdminServiceStubMock._getServiceClient()).thenReturn(serviceClientMock);

        genericObjectPoolMock = Mockito.mock(GenericObjectPool.class);
        when(genericObjectPoolMock.borrowObject()).thenReturn(oAuthAdminServiceStubMock);
    }

    @AfterMethod
    public void tearDown() {
        genericObjectPoolMock = null;
        oAuthConsumerAppDTOMock = null;
        oAuthAdminServiceStubMock = null;
    }

    @Test
    public void testGetApplicationData_shouldReturnAppDto_whenPreviousCookieNotExists() throws PCRException, RemoteException, OAuthAdminServiceException {
        OAuthConsumerAppDTO oAuthConsumerAppDTO = new OAuthApplicationData(genericObjectPoolMock).getApplicationData("test-app");
        Assertions.assertThat(oAuthConsumerAppDTO).isEqualTo(oAuthConsumerAppDTOMock);
        verify(oAuthAdminServiceStubMock, times(1)).getOAuthApplicationData("test-app");
        Assertions.assertThat(field("cookie").ofType(String.class).in(OAuthApplicationData.class).get()).isEqualTo("mocked-cookie");
    }

    @Test
    public void testGetApplicationData_shouldReturnAppDtoAndSetCookie_whenPreviousCookieExists() throws PCRException, RemoteException, OAuthAdminServiceException {
        field("cookie").ofType(String.class).in(OAuthApplicationData.class).set("already-exists-cookie");
        OAuthConsumerAppDTO oAuthConsumerAppDTO = new OAuthApplicationData(genericObjectPoolMock).getApplicationData("test-app");
        Assertions.assertThat(oAuthConsumerAppDTO).isEqualTo(oAuthConsumerAppDTOMock);
        verify(oAuthAdminServiceStubMock, times(1)).getOAuthApplicationData("test-app");
        verify(optionsMock, times(1)).setProperty(HTTPConstants.COOKIE_STRING, "already-exists-cookie");
        Assertions.assertThat(field("cookie").ofType(String.class).in(OAuthApplicationData.class).get()).isEqualTo("mocked-cookie");
    }
}