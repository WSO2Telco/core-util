package com.wso2telco.core.pcrservice.util;

import org.assertj.core.api.Assertions;
import org.fest.reflect.reference.TypeRef;
import org.mockito.Mockito;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import static org.fest.reflect.core.Reflection.field;
import static org.mockito.Mockito.mock;
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
public class RedisUtilTest {

    @Test
    public void testGetInstance_shouldReturnJedisPool_ifAlreadyInitialized() {
        Jedis jedis = Mockito.mock(Jedis.class);
        JedisPool jedisPool = mock(JedisPool.class);
        when(jedisPool.getResource()).thenReturn(jedis);
        field("pool").ofType(new TypeRef<JedisPool>() {}).in(RedisUtil.class).set(jedisPool);

        Assertions.assertThat(RedisUtil.getInstance()).isEqualTo(jedisPool);

        jedis = null;
        field("pool").ofType(new TypeRef<JedisPool>() {}).in(RedisUtil.class).set(null);
    }
}