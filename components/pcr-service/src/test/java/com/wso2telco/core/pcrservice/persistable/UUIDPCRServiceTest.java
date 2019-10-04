package com.wso2telco.core.pcrservice.persistable;

import java.util.ArrayList;
import java.util.List;

import com.wso2telco.core.pcrservice.dao.impl.KeyValueBasedPcrDAOImpl;
import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;
import com.wso2telco.core.pcrservice.oauth.OAuthApplicationData;
import com.wso2telco.core.pcrservice.util.RedisUtil;
import org.assertj.core.api.Assertions;
import org.fest.reflect.reference.TypeRef;
import org.mockito.Mockito;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.oauth.stub.dto.OAuthConsumerAppDTO;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import static org.fest.reflect.core.Reflection.field;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
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
public class UUIDPCRServiceTest {

    private Jedis jedis;
    OAuthApplicationData oAuthApplicationDataMock;

    @BeforeMethod
    public void setUp() throws Exception {
        jedis = Mockito.mock(Jedis.class);
        JedisPool jedisPool = mock(JedisPool.class);
        when(jedisPool.getResource()).thenReturn(jedis);
        field("pool").ofType(new TypeRef<JedisPool>() {}).in(RedisUtil.class).set(jedisPool);

        OAuthConsumerAppDTO oAuthConsumerAppDtoMock = Mockito.mock(OAuthConsumerAppDTO.class);
        when(oAuthConsumerAppDtoMock.getCallbackUrl()).thenReturn("www.test-url.com");

        oAuthApplicationDataMock = Mockito.mock(OAuthApplicationData.class);
        when(oAuthApplicationDataMock.getApplicationData(anyString())).thenReturn(oAuthConsumerAppDtoMock);
    }

    @AfterMethod
    public void tearDown() {
        jedis = null;
        field("pool").ofType(new TypeRef<JedisPool>() {}).in(RedisUtil.class).set(null);
    }

    @Test(expectedExceptions = PCRException.class, expectedExceptionsMessageRegExp = "Mandatory parameters are empty")
    public void testGetPar_shouldThrowParException_whenMandatoryParametersAreEmpty() throws PCRException {
        RequestDTO requestDtoMock = Mockito.mock(RequestDTO.class);
        new UUIDPCRService(oAuthApplicationDataMock).getPcr(requestDtoMock);
    }

    @Test
    public void testGetPcr_shouldReturnExistingPcr_IfThereAny() throws PCRException {
        RequestDTO requestDtoMock = Mockito.mock(RequestDTO.class);
        when(requestDtoMock.getUserId()).thenReturn("123");
        when(requestDtoMock.getSectorId()).thenReturn("456");
        when(requestDtoMock.getAppId()).thenReturn("789");

        KeyValueBasedPcrDAOImpl keyValueBasedPcrDAO = Mockito.mock(KeyValueBasedPcrDAOImpl.class);
        when(keyValueBasedPcrDAO.getExistingPCR(any(RequestDTO.class))).thenReturn("test-pcr");

        Assertions.assertThat(new UUIDPCRService(oAuthApplicationDataMock).getPcr(
            requestDtoMock,
            keyValueBasedPcrDAO
        )).isEqualTo("test-pcr");
        verify(keyValueBasedPcrDAO, times(1)).getExistingPCR(requestDtoMock);
    }

    @Test
    public void testGetPcr_shouldCreateAndPersistNewPcr_IfAnyPcrNotExistsAndAppIdListIsEmpty() throws PCRException {
        RequestDTO requestDtoMock = Mockito.mock(RequestDTO.class);
        when(requestDtoMock.getUserId()).thenReturn("123");
        when(requestDtoMock.getSectorId()).thenReturn("456");
        when(requestDtoMock.getAppId()).thenReturn("789");

        KeyValueBasedPcrDAOImpl keyValueBasedPcrDAO = Mockito.mock(KeyValueBasedPcrDAOImpl.class);
        when(keyValueBasedPcrDAO.getExistingPCR(any(RequestDTO.class))).thenReturn(null);
        when(keyValueBasedPcrDAO.getAppIdListForUserSectorCombination(anyString(), anyString())).thenReturn(new ArrayList<>());

        Assertions.assertThat(new UUIDPCRService(oAuthApplicationDataMock).getPcr(
            requestDtoMock,
            keyValueBasedPcrDAO
        )).isNotNull();
        verify(keyValueBasedPcrDAO, times(1)).getExistingPCR(requestDtoMock);
        // update service-provider-map
        verify(jedis, times(1)).rpush("www.test-url.com", "789:true");
        // create and persist new pcr
        verify(jedis, times(1)).rpush(eq("123:456"), startsWith("789:"));
        // update pcr-msisdn map
        verify(jedis, times(1)).rpush(startsWith("456:"), eq("123"));
    }

    @Test
    public void testGetPcr_shouldUpdateServiceProviderMap_IfAnyPcrNotExistsAndAppIdListIsNotEmptyAndApplicationNotExists() throws PCRException {
        RequestDTO requestDtoMock = Mockito.mock(RequestDTO.class);
        when(requestDtoMock.getUserId()).thenReturn("123");
        when(requestDtoMock.getSectorId()).thenReturn("456");
        when(requestDtoMock.getAppId()).thenReturn("789");

        List<String> appIdList = new ArrayList<>();
        appIdList.add("app-id-1");
        appIdList.add("app-id-2");
        appIdList.add("app-id-3");

        KeyValueBasedPcrDAOImpl keyValueBasedPcrDAO = Mockito.mock(KeyValueBasedPcrDAOImpl.class);
        when(keyValueBasedPcrDAO.getExistingPCR(any(RequestDTO.class))).thenReturn(null);
        when(keyValueBasedPcrDAO.getAppIdListForUserSectorCombination(anyString(), anyString())).thenReturn(appIdList);
        when(keyValueBasedPcrDAO.checkApplicationExists(anyString(), anyString())).thenReturn(false);

        new UUIDPCRService(oAuthApplicationDataMock).getPcr(requestDtoMock, keyValueBasedPcrDAO);
        verify(keyValueBasedPcrDAO, times(1)).getExistingPCR(requestDtoMock);
        verify(jedis, times(1)).rpush("www.test-url.com", "789:true");
    }

    @Test
    public void testGetPcr_shouldCreateAndPersistNewPcr_IfAnyPcrNotExistsAndAppIdListIsNotEmptyAndAppIsNotRelated() throws PCRException {
        RequestDTO requestDtoMock = Mockito.mock(RequestDTO.class);
        when(requestDtoMock.getUserId()).thenReturn("123");
        when(requestDtoMock.getSectorId()).thenReturn("456");
        when(requestDtoMock.getAppId()).thenReturn("789");

        List<String> appIdList = new ArrayList<>();
        appIdList.add("app-id-1");
        appIdList.add("app-id-2");
        appIdList.add("app-id-3");

        KeyValueBasedPcrDAOImpl keyValueBasedPcrDAO = Mockito.mock(KeyValueBasedPcrDAOImpl.class);
        when(keyValueBasedPcrDAO.getExistingPCR(any(RequestDTO.class))).thenReturn(null);
        when(keyValueBasedPcrDAO.getAppIdListForUserSectorCombination(anyString(), anyString())).thenReturn(appIdList);
        when(keyValueBasedPcrDAO.checkIsRelated(anyString(), anyString())).thenReturn(false);

        new UUIDPCRService(oAuthApplicationDataMock).getPcr(requestDtoMock, keyValueBasedPcrDAO);
        // create and persist new pcr
        verify(jedis, times(1)).rpush(eq("123:456"), startsWith("789:"));
        // update pcr-msisdn map
        verify(jedis, times(1)).rpush(startsWith("456:"), eq("123"));
    }

    @Test
    public void testGetPcr_shouldReturnExistingPcrWhileCreateAndPersistNewPcr_IfAnyPcrNotExistsAndAppIdListIsNotEmptyAndAppIsRelatedAndRelatedAppExists() throws PCRException {
        RequestDTO requestDtoMock = Mockito.mock(RequestDTO.class);
        when(requestDtoMock.getUserId()).thenReturn("123");
        when(requestDtoMock.getSectorId()).thenReturn("456");
        when(requestDtoMock.getAppId()).thenReturn("789");

        List<String> appIdList = new ArrayList<>();
        appIdList.add("app-id-1");
        appIdList.add("app-id-2");
        appIdList.add("app-id-3");

        KeyValueBasedPcrDAOImpl keyValueBasedPcrDAO = Mockito.mock(KeyValueBasedPcrDAOImpl.class);
        when(keyValueBasedPcrDAO.getExistingPCR(any(RequestDTO.class))).thenReturn("test-pcr");
        when(keyValueBasedPcrDAO.getExistingPCR(requestDtoMock)).thenReturn(null);
        when(keyValueBasedPcrDAO.getAppIdListForUserSectorCombination(anyString(), anyString())).thenReturn(appIdList);
        when(keyValueBasedPcrDAO.checkIsRelated(anyString(), anyString())).thenReturn(false);
        when(keyValueBasedPcrDAO.checkIsRelated(anyString(), eq("789"))).thenReturn(true);
        when(keyValueBasedPcrDAO.checkIsRelated(anyString(), eq("app-id-1"))).thenReturn(true);

        // return existing pcr
        Assertions.assertThat(new UUIDPCRService(oAuthApplicationDataMock).getPcr(
            requestDtoMock,
            keyValueBasedPcrDAO
        )).isEqualTo("test-pcr");
        // create and persist new pcr
        verify(jedis, times(1)).rpush(eq("123:456"), startsWith("789:"));
    }

    @Test
    public void testGetPcr_shouldReturnExistingPcrWhileCreateAndPersistNewPcr_IfAnyPcrNotExistsAndAppIdListIsNotEmptyAndAppIsRelatedAndRelatedAppNotExists() throws PCRException {
        RequestDTO requestDtoMock = Mockito.mock(RequestDTO.class);
        when(requestDtoMock.getUserId()).thenReturn("123");
        when(requestDtoMock.getSectorId()).thenReturn("456");
        when(requestDtoMock.getAppId()).thenReturn("789");

        List<String> appIdList = new ArrayList<>();
        appIdList.add("app-id-1");
        appIdList.add("app-id-2");
        appIdList.add("app-id-3");

        KeyValueBasedPcrDAOImpl keyValueBasedPcrDAO = Mockito.mock(KeyValueBasedPcrDAOImpl.class);
        when(keyValueBasedPcrDAO.getExistingPCR(any(RequestDTO.class))).thenReturn("test-pcr");
        when(keyValueBasedPcrDAO.getExistingPCR(requestDtoMock)).thenReturn(null);
        when(keyValueBasedPcrDAO.getAppIdListForUserSectorCombination(anyString(), anyString())).thenReturn(appIdList);
        when(keyValueBasedPcrDAO.checkIsRelated(anyString(), anyString())).thenReturn(false);
        when(keyValueBasedPcrDAO.checkIsRelated(anyString(), eq("789"))).thenReturn(true);
        when(keyValueBasedPcrDAO.checkIsRelated(anyString(), eq("app-id-1"))).thenReturn(false);

        Assertions.assertThat(new UUIDPCRService(oAuthApplicationDataMock).getPcr(
            requestDtoMock,
            keyValueBasedPcrDAO
        )).isNotNull();
        // create and persist new pcr
        verify(jedis, times(1)).rpush(eq("123:456"), startsWith("789:"));
        // update pcr-msisdn map
        verify(jedis, times(1)).rpush(startsWith("456:"), eq("123"));
    }

    @Test
    public void testGetExistingPCR_shouldReturnExistingPcr_always() throws PCRException {
        RequestDTO requestDtoMock = Mockito.mock(RequestDTO.class);

        KeyValueBasedPcrDAOImpl keyValueBasedPcrDaoMock = Mockito.mock(KeyValueBasedPcrDAOImpl.class);
        when(keyValueBasedPcrDaoMock.getExistingPCR(any(RequestDTO.class))).thenReturn("test-pcr-1");

        Assertions.assertThat(new UUIDPCRService(oAuthApplicationDataMock).getExistingPCR(
            requestDtoMock,
            keyValueBasedPcrDaoMock
        )).isEqualTo("test-pcr-1");
        verify(keyValueBasedPcrDaoMock, times(1)).getExistingPCR(requestDtoMock);
    }

    @Test
    public void testGetMsisdnByPcr_shouldReturnMsisdnByPcr_always() throws PCRException {
        RequestDTO requestDtoMock = Mockito.mock(RequestDTO.class);

        KeyValueBasedPcrDAOImpl keyValueBasedPcrDaoMock = Mockito.mock(KeyValueBasedPcrDAOImpl.class);
        when(keyValueBasedPcrDaoMock.getMSISDNbyPcr(anyString(), anyString())).thenReturn("test-msisdn-1");

        Assertions.assertThat(new UUIDPCRService(oAuthApplicationDataMock).getMsisdnByPcr(
            "test-sector-id",
            "test-pcr-1",
            keyValueBasedPcrDaoMock
        )).isEqualTo("test-msisdn-1");
        verify(keyValueBasedPcrDaoMock, times(1)).getMSISDNbyPcr("test-sector-id", "test-pcr-1");
    }
}