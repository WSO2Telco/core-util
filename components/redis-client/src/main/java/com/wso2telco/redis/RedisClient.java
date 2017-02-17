package com.wso2telco.redis;

import redis.clients.jedis.Jedis;

/**
 * This class has the apis to perform different operations to Redis database
 */
public class RedisClient {

    /**
     * Sets a new key-value pair with a time to live.
     *
     * @param key   The name of the key
     * @param value The string value of the key
     * @param ttl   The time to live of the key in seconds
     */
    public static void setKey(String key, String value, int ttl) {
        try (Jedis jedis = RedisConnection.getJedisPool().getResource()) {
            //jedis.set(key, value);
            //jedis.expire(key, ttl);
            jedis.setex(key, ttl, value);

        }

    }

    /**
     * Returns a value of a key. Returns null if key does not exists.
     *
     * @param key The name of the key
     * @return The value of the key
     */
    public static String getKey(String key) {
        try (Jedis jedis = RedisConnection.getJedisPool().getResource()) {
            return jedis.get(key);
        }
    }

    /**
     * @param key The name of the key
     * @return The remaining time to live of the key in seconds. -2 if the key does not exist.-1 if the key exists but has no associated expire.
     */
    public static long getTtl(String key) {
        try (Jedis jedis = RedisConnection.getJedisPool().getResource()) {
            return jedis.ttl(key);
        }
    }
}
