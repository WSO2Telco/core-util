package com.wso2telco.core.pcrservice.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wso2telco.core.pcrservice.exception.PCRException;
import com.wso2telco.core.pcrservice.model.RequestDTO;
import com.wso2telco.core.pcrservice.util.RedisUtil;
import org.assertj.core.api.Assertions;
import org.fest.reflect.reference.TypeRef;
import org.mockito.Mockito;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import static org.fest.reflect.core.Reflection.field;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
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
public class KeyValueBasedPcrDAOImplTest {

    private Jedis jedis;

    @BeforeMethod
    public void setUp() {
        jedis = Mockito.mock(Jedis.class);
        JedisPool jedisPool = mock(JedisPool.class);
        when(jedisPool.getResource()).thenReturn(jedis);
        field("pool").ofType(new TypeRef<JedisPool>() {}).in(RedisUtil.class).set(jedisPool);
    }

    @AfterMethod
    public void tearDown() {
        jedis = null;
        field("pool").ofType(new TypeRef<JedisPool>() {}).in(RedisUtil.class).set(null);
    }

    @Test
    public void testCreateNewPcrEntry_shouldPushPcrValueAndCloseRedisConnection_always() throws PCRException, IOException {
        RequestDTO mockRequestDto = Mockito.mock(RequestDTO.class);
        when(mockRequestDto.getUserId()).thenReturn("123");
        when(mockRequestDto.getSectorId()).thenReturn("456");
        when(mockRequestDto.getAppId()).thenReturn("789");

        when(jedis.rpush(anyString())).thenReturn(1L);

        new KeyValueBasedPcrDAOImpl().createNewPcrEntry(mockRequestDto, "test-pcr-value");
        verify(jedis, times(1)).rpush("123:456","789:test-pcr-value");
        verify(jedis, times(1)).close();
    }

    @Test
    public void testGetExistingPCR_shouldReturnPcr_forValidParameters() throws PCRException {
        RequestDTO mockRequestDto = Mockito.mock(RequestDTO.class);
        when(mockRequestDto.getUserId()).thenReturn("123");
        when(mockRequestDto.getSectorId()).thenReturn("456");
        when(mockRequestDto.getAppId()).thenReturn("789");

        List<String> pcrList = new ArrayList<>();
        pcrList.add("234:test-pcr-value-1");
        pcrList.add("567:test-pcr-value-2");
        pcrList.add("789:test-pcr-value-3");
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(pcrList);

        Assertions.assertThat(new KeyValueBasedPcrDAOImpl().getExistingPCR(mockRequestDto)).isEqualTo("test-pcr-value-3");
        verify(jedis, times(1)).lrange("123:456", 0, -1);
        verify(jedis, times(1)).close();
    }

    @Test
    public void testGetExistingPCR_shouldReturnNull_forInvalidParameters() throws PCRException {
        RequestDTO mockRequestDto = Mockito.mock(RequestDTO.class);
        when(mockRequestDto.getUserId()).thenReturn("123");
        when(mockRequestDto.getSectorId()).thenReturn("456");
        when(mockRequestDto.getAppId()).thenReturn("789");

        List<String> pcrList = new ArrayList<>();
        pcrList.add("234:test-pcr-value-1");
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(pcrList);

        Assertions.assertThat(new KeyValueBasedPcrDAOImpl().getExistingPCR(mockRequestDto)).isNull();
        verify(jedis, times(1)).lrange("123:456", 0, -1);
        verify(jedis, times(1)).close();
    }

    @Test
    public void testGetApplicationIdList_shouldReturnAppIdList_forValidSectorId() throws PCRException {
        List<String> pcrList = new ArrayList<>();
        pcrList.add("234:test-pcr-value-1");
        pcrList.add("567:test-pcr-value-2");
        pcrList.add("789:test-pcr-value-3");
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(pcrList);

        List<String> appIdList = new ArrayList<>();
        appIdList.add("234");
        appIdList.add("567");
        appIdList.add("789");

        Assertions.assertThat(new KeyValueBasedPcrDAOImpl().getApplicationIdList("123")).isEqualTo(appIdList);
        verify(jedis, times(1)).lrange("123", 0, -1);
        verify(jedis, times(1)).close();
    }

    @Test
    public void testGetApplicationIdList_shouldReturnEmptyList_forInvalidSectorId() throws PCRException {
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(new ArrayList<>());

        Assertions.assertThat(new KeyValueBasedPcrDAOImpl().getApplicationIdList("456")).isEqualTo(new ArrayList<>());
        verify(jedis, times(1)).lrange("456", 0, -1);
        verify(jedis, times(1)).close();
    }

    @Test
    public void testGetApplicationIdList_shouldReturnEmptyList_forNullSectorId() throws PCRException {
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(new ArrayList<>());

        Assertions.assertThat(new KeyValueBasedPcrDAOImpl().getApplicationIdList(null)).isEqualTo(new ArrayList<>());
        verify(jedis, times(1)).close();
    }

    @Test
    public void testCheckIsRelated_shouldReturnTrue_ifPcrTrueForGivenSectorAndAppIds() throws PCRException {
        List<String> pcrList = new ArrayList<>();
        pcrList.add("123:true");
        pcrList.add("456:true");
        pcrList.add("789:false");
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(pcrList);

        Assertions.assertThat(new KeyValueBasedPcrDAOImpl().checkIsRelated("456", "123")).isEqualTo(true);
        verify(jedis, times(1)).lrange("456", 0, -1);
        verify(jedis, times(1)).close();
    }

    @Test
    public void testCheckIsRelated_shouldReturnFalse_ifPcrFalseForGivenSectorAndAppIds() throws PCRException {
        List<String> pcrList = new ArrayList<>();
        pcrList.add("123:false");
        pcrList.add("567:true");
        pcrList.add("678:false");
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(pcrList);

        Assertions.assertThat(new KeyValueBasedPcrDAOImpl().checkIsRelated("456", "123")).isEqualTo(false);
        verify(jedis, times(1)).lrange("456", 0, -1);
        verify(jedis, times(1)).close();
    }

    @Test
    public void testCheckApplicationExists_shouldReturnTrue_ifAppIdExistsForSectorId() throws PCRException {
        List<String> pcrList = new ArrayList<>();
        pcrList.add("123:false");
        pcrList.add("567:true");
        pcrList.add("678:false");
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(pcrList);

        Assertions.assertThat(new KeyValueBasedPcrDAOImpl().checkApplicationExists("456", "123")).isEqualTo(true);
        verify(jedis, times(1)).lrange("456", 0, -1);
        verify(jedis, times(1)).close();
    }

    @Test
    public void testCheckApplicationExists_shouldReturnFalse_ifAppIdNotExistsForSectorId() throws PCRException {
        List<String> pcrList = new ArrayList<>();
        pcrList.add("345:false");
        pcrList.add("567:true");
        pcrList.add("678:false");
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(pcrList);

        Assertions.assertThat(new KeyValueBasedPcrDAOImpl().checkApplicationExists("456", "123")).isEqualTo(false);
        verify(jedis, times(1)).lrange("456", 0, -1);
        verify(jedis, times(1)).close();
    }

    @Test
    public void testCreateNewSPEntry_shouldPushSpEntryValueAndCloseRedisConnection_always() throws PCRException {
        when(jedis.rpush(anyString())).thenReturn(1L);

        new KeyValueBasedPcrDAOImpl().createNewSPEntry("123", "456", true);
        verify(jedis, times(1)).rpush("123","456:true");
        verify(jedis, times(1)).close();
    }

    @Test
    public void testGetAppIdListForUserSectorCombination_shouldReturnAppIdList_forValidUserAndSectorIds() throws PCRException {
        List<String> pcrList = new ArrayList<>();
        pcrList.add("234:test-pcr-value-1");
        pcrList.add("567:test-pcr-value-2");
        pcrList.add("789:test-pcr-value-3");
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(pcrList);

        List<String> appIdList = new ArrayList<>();
        appIdList.add("234");
        appIdList.add("567");
        appIdList.add("789");

        Assertions.assertThat(new KeyValueBasedPcrDAOImpl().getAppIdListForUserSectorCombination("123", "456")).isEqualTo(appIdList);
        verify(jedis, times(1)).lrange("123:456", 0, -1);
        verify(jedis, times(1)).close();
    }

    @Test
    public void testGetAppIdListForUserSectorCombination_shouldReturnEmptyList_forInvalidUserAndSectorIds() throws PCRException {
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(new ArrayList<>());

        Assertions.assertThat(new KeyValueBasedPcrDAOImpl().getAppIdListForUserSectorCombination("123", "456")).isEqualTo(new ArrayList<>());
        verify(jedis, times(1)).lrange("123:456", 0, -1);
        verify(jedis, times(1)).close();
    }

    @Test
    public void testCreateNewPCRMSISDNEntry_shouldPushPcrValueAndCloseRedisConnection_always() throws PCRException {
        when(jedis.rpush(anyString())).thenReturn(1L);

        new KeyValueBasedPcrDAOImpl().createNewPCRMSISDNEntry("123", "456", "test-pcr-value-1");
        verify(jedis, times(1)).rpush("456:test-pcr-value-1", "123");
        verify(jedis, times(1)).close();
    }

    @Test
    public void testGetMSISDNbyPcr_shouldReturnLastMsisdn_forValidSectorIdAndPcrValue() throws PCRException {
        List<String> pcrList = new ArrayList<>();
        pcrList.add("+94715902989");
        pcrList.add("+94714907064");
        pcrList.add("+94714907034");
        when(jedis.lrange(anyString(), anyLong(), anyLong())).thenReturn(pcrList);

        Assertions.assertThat(new KeyValueBasedPcrDAOImpl().getMSISDNbyPcr("123", "test-pcr-value")).isEqualTo("+94714907034");
        verify(jedis, times(1)).lrange("123:test-pcr-value", 0, -1);
        verify(jedis, times(1)).close();
    }
}