package com.wso2telco.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * This class is used to connect to the Redis database and return a Jedis connection pool
 */
public class RedisConnection {

    private static final String REDIS_PROPERTIES_FILE = "repository/conf/redis-config.properties";

    private static JedisPool jedisPool;
    private static Properties prop;


    /**
     * Returns a pool of Jedis connections.
     * Create Create a new pool of Jedis connections if jedisPool is null.
     *
     * @return JedisPool
     */
    protected static JedisPool getJedisPool() {

        if (null == jedisPool) {
            createJedisPool();

        }
        return jedisPool;

    }

    /**
     * Create a new pool of Jedis connections.
     */
    private static void createJedisPool() {

        loadRedisProperties();

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        String host = prop.getProperty("host");
        int port = Integer.parseInt(prop.getProperty("port"));
        int timeout = Protocol.DEFAULT_TIMEOUT;
        String password;
        if (prop.getProperty("password").equals("")) {
            password = null;
        } else {
            password = prop.getProperty("password");
        }

        jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);

    }

    /**
     * Reads redis-config property file
     */
    private static void loadRedisProperties() {

        if (null == prop) {
            try (InputStream input = new FileInputStream(REDIS_PROPERTIES_FILE)) {
                prop = new Properties();
                prop.load(input);

            } catch (IOException e) {

            }

        }

    }


}
