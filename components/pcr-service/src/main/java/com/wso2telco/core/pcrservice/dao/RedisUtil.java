package com.wso2telco.core.pcrservice.dao;

import com.wso2telco.core.pcrservice.util.YamlReader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtil {

	private static JedisPool pool = null;
	private static Jedis jedis = null;

	static {
		String redisHost = YamlReader.getConfiguration().getRedisHost();
		int redisPort = YamlReader.getConfiguration().getRedisPort();
		pool = new JedisPool(redisHost, redisPort);
		jedis = pool.getResource();
	}

	public static Jedis getInstance() {
		return jedis;
	}
}
