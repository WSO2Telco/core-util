package com.wso2telco.core.pcrservice.util;

import java.util.Map;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    private static volatile JedisPool pool = null;

    public static JedisPool getInstance() {
        if (pool == null) {
            synchronized (RedisUtil.class) {
                if (pool == null) {
                    Map<Object, Object> redis = (Map<Object, Object>) YamlReader.getConfiguration().getRedis();
                    String redisHost = (String) redis.get("host");
                    int redisPort = (int) redis.get("port");
                    String password = (String) redis.get("password");
                    int timeout = (int) redis.get("timeout");
                    int jedisPoolSize = (int) redis.get("poolsize");
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(jedisPoolSize);
                    pool = new JedisPool(new GenericObjectPoolConfig(), redisHost, redisPort, timeout, password);
                }
            }
        }
        return pool;
    }
}
