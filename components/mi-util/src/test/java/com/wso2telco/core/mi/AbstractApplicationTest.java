package com.wso2telco.core.mi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import io.dropwizard.Configuration;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.jetty.ConnectorFactory;
import io.dropwizard.jetty.HttpConnectorFactory;
import io.dropwizard.jetty.HttpsConnectorFactory;
import io.dropwizard.server.DefaultServerFactory;
import io.dropwizard.server.ServerFactory;
import io.dropwizard.server.SimpleServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;
import org.fest.reflect.reference.TypeRef;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.fest.reflect.core.Reflection.field;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doCallRealMethod;
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
public class AbstractApplicationTest {

    @Mock AbstractApplication<ConfigDTO> abstractApplicationImpl;
    @Mock SwaggerDropwizard<Configuration> swaggerDropwizardMock;
    private final static int RESTFUL_COMPONENT_COUNT = 3;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        doCallRealMethod().when(abstractApplicationImpl).run(any(ConfigDTO.class), any(Environment.class));
        doCallRealMethod().when(abstractApplicationImpl).initialize(any());

        field("swaggerDropwizard").ofType(new TypeRef<SwaggerDropwizard>() {}).in(abstractApplicationImpl).set(swaggerDropwizardMock);

        List<Object> objectList = new ArrayList<>();
        IntStream.rangeClosed(1, RESTFUL_COMPONENT_COUNT).forEach(i -> objectList.add(Mockito.mock(Object.class)));
        when(abstractApplicationImpl.getRestFulComponents()).thenReturn(objectList);
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testRun_shouldRun_forSimpleServerFactoryAndHttpConnectorFactory() throws Exception {
        HttpConnectorFactory httpConnectorFactoryMock = Mockito.mock(HttpConnectorFactory.class);

        SimpleServerFactory simpleServerFactoryMock = Mockito.mock(SimpleServerFactory.class);
        when(simpleServerFactoryMock.getConnector()).thenReturn(httpConnectorFactoryMock);

        ConfigDTO configDtoMock = Mockito.mock(ConfigDTO.class);
        when(configDtoMock.getServerFactory()).thenReturn(simpleServerFactoryMock);

        JerseyEnvironment jerseyEnvironmentMock = Mockito.mock(JerseyEnvironment.class);

        Environment environmentMock = Mockito.mock(Environment.class);
        when(environmentMock.jersey()).thenReturn(jerseyEnvironmentMock);

        abstractApplicationImpl.run(configDtoMock, environmentMock);

        verify(abstractApplicationImpl, times(1)).runInit(configDtoMock, environmentMock);
        verify(jerseyEnvironmentMock, times(RESTFUL_COMPONENT_COUNT + 1)).register(any(Object.class));
        verify(swaggerDropwizardMock, times(1)).onRun(configDtoMock, environmentMock);
    }

    @Test
    public void testRun_shouldRun_forSimpleServerFactoryAndHttpsConnectorFactory() throws Exception {
        HttpsConnectorFactory httpsConnectorFactoryMock = Mockito.mock(HttpsConnectorFactory.class);

        SimpleServerFactory simpleServerFactoryMock = Mockito.mock(SimpleServerFactory.class);
        when(simpleServerFactoryMock.getConnector()).thenReturn(httpsConnectorFactoryMock);

        ConfigDTO configDtoMock = Mockito.mock(ConfigDTO.class);
        when(configDtoMock.getServerFactory()).thenReturn(simpleServerFactoryMock);

        JerseyEnvironment jerseyEnvironmentMock = Mockito.mock(JerseyEnvironment.class);

        Environment environmentMock = Mockito.mock(Environment.class);
        when(environmentMock.jersey()).thenReturn(jerseyEnvironmentMock);

        abstractApplicationImpl.run(configDtoMock, environmentMock);

        verify(abstractApplicationImpl, times(1)).runInit(configDtoMock, environmentMock);
        verify(jerseyEnvironmentMock, times(RESTFUL_COMPONENT_COUNT + 1)).register(any(Object.class));
        verify(swaggerDropwizardMock, times(1)).onRun(configDtoMock, environmentMock);
    }

    @Test
    public void testRun_shouldRun_forDefaultServerFactoryAndHttpConnectorFactory() throws Exception {
        HttpConnectorFactory httpConnectorFactoryMock = Mockito.mock(HttpConnectorFactory.class);

        DefaultServerFactory defaultServerFactoryMock = Mockito.mock(DefaultServerFactory.class);
        when(defaultServerFactoryMock.getApplicationConnectors()).thenReturn(Collections.singletonList(httpConnectorFactoryMock));

        ConfigDTO configDtoMock = Mockito.mock(ConfigDTO.class);
        when(configDtoMock.getServerFactory()).thenReturn(defaultServerFactoryMock);

        JerseyEnvironment jerseyEnvironmentMock = Mockito.mock(JerseyEnvironment.class);

        Environment environmentMock = Mockito.mock(Environment.class);
        when(environmentMock.jersey()).thenReturn(jerseyEnvironmentMock);

        abstractApplicationImpl.run(configDtoMock, environmentMock);

        verify(abstractApplicationImpl, times(1)).runInit(configDtoMock, environmentMock);
        verify(jerseyEnvironmentMock, times(RESTFUL_COMPONENT_COUNT + 1)).register(any(Object.class));
        verify(swaggerDropwizardMock, times(1)).onRun(configDtoMock, environmentMock);
    }

    @Test
    public void testRun_shouldRun_forDefaultServerFactoryAndHttpsConnectorFactory() throws Exception {
        HttpsConnectorFactory httpsConnectorFactoryMock = Mockito.mock(HttpsConnectorFactory.class);

        DefaultServerFactory defaultServerFactoryMock = Mockito.mock(DefaultServerFactory.class);
        when(defaultServerFactoryMock.getApplicationConnectors()).thenReturn(Collections.singletonList(httpsConnectorFactoryMock));

        ConfigDTO configDtoMock = Mockito.mock(ConfigDTO.class);
        when(configDtoMock.getServerFactory()).thenReturn(defaultServerFactoryMock);

        JerseyEnvironment jerseyEnvironmentMock = Mockito.mock(JerseyEnvironment.class);

        Environment environmentMock = Mockito.mock(Environment.class);
        when(environmentMock.jersey()).thenReturn(jerseyEnvironmentMock);

        abstractApplicationImpl.run(configDtoMock, environmentMock);

        verify(abstractApplicationImpl, times(1)).runInit(configDtoMock, environmentMock);
        verify(jerseyEnvironmentMock, times(RESTFUL_COMPONENT_COUNT + 1)).register(any(Object.class));
        verify(swaggerDropwizardMock, times(1)).onRun(configDtoMock, environmentMock);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testRun_shouldThrowException_forInvalidServerFactory() throws Exception {
        ServerFactory serverFactoryMock = Mockito.mock(ServerFactory.class);

        ConfigDTO configDtoMock = Mockito.mock(ConfigDTO.class);
        when(configDtoMock.getServerFactory()).thenReturn(serverFactoryMock);

        JerseyEnvironment jerseyEnvironmentMock = Mockito.mock(JerseyEnvironment.class);

        Environment environmentMock = Mockito.mock(Environment.class);
        when(environmentMock.jersey()).thenReturn(jerseyEnvironmentMock);

        abstractApplicationImpl.run(configDtoMock, environmentMock);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testRun_shouldThrowException_forSimpleServerFactoryAndInvalidConnectorFactory() throws Exception {
        ConnectorFactory connectorFactoryMock = Mockito.mock(ConnectorFactory.class);

        SimpleServerFactory simpleServerFactoryMock = Mockito.mock(SimpleServerFactory.class);
        when(simpleServerFactoryMock.getConnector()).thenReturn(connectorFactoryMock);

        ConfigDTO configDtoMock = Mockito.mock(ConfigDTO.class);
        when(configDtoMock.getServerFactory()).thenReturn(simpleServerFactoryMock);

        JerseyEnvironment jerseyEnvironmentMock = Mockito.mock(JerseyEnvironment.class);

        Environment environmentMock = Mockito.mock(Environment.class);
        when(environmentMock.jersey()).thenReturn(jerseyEnvironmentMock);

        abstractApplicationImpl.run(configDtoMock, environmentMock);
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testRun_shouldThrowException_forDefaultServerFactoryAndInvalidConnectorFactory() throws Exception {
        ConnectorFactory connectorFactoryMock = Mockito.mock(ConnectorFactory.class);

        DefaultServerFactory defaultServerFactoryMock = Mockito.mock(DefaultServerFactory.class);
        when(defaultServerFactoryMock.getApplicationConnectors()).thenReturn(Collections.singletonList(connectorFactoryMock));

        ConfigDTO configDtoMock = Mockito.mock(ConfigDTO.class);
        when(configDtoMock.getServerFactory()).thenReturn(defaultServerFactoryMock);

        JerseyEnvironment jerseyEnvironmentMock = Mockito.mock(JerseyEnvironment.class);

        Environment environmentMock = Mockito.mock(Environment.class);
        when(environmentMock.jersey()).thenReturn(jerseyEnvironmentMock);

        abstractApplicationImpl.run(configDtoMock, environmentMock);
    }

    @Test
    public void testInitialize_shouldInitSwaggerDropWizard_always() {
        Bootstrap bootstrapMock = Mockito.mock(Bootstrap.class);
        abstractApplicationImpl.initialize(bootstrapMock);

        verify(swaggerDropwizardMock, times(1)).onInitialize(bootstrapMock);
    }
}