package com.wso2telco.core.pcrservice.dao;

import java.util.Map;

import com.wso2telco.core.pcrservice.util.YamlReader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtil {

	private static JedisPool pool = null;
	private static Jedis jedis = null;

	static {
		Map<Object,Object> redis = (Map<Object, Object>) YamlReader.getConfiguration().getRedis();
		String redisHost = (String) redis.get("host");
		int redisPort = (int) redis.get("port");
		pool = new JedisPool(redisHost, redisPort);
		jedis = pool.getResource();
	}

	public static Jedis getInstance() {
		return jedis;
	}
}
