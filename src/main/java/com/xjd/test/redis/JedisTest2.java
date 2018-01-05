package com.xjd.test.redis;

import java.util.Arrays;
import java.util.HashSet;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;


/**
 * @author elvis.xu
 * @since 2017-11-27 18:29
 */
public class JedisTest2 {
	public static void main(String[] args) {

		JedisSentinelPool pool = new JedisSentinelPool("mymaster", new HashSet<>(Arrays.asList("service4:26380","service4:26381","service4:26382")), "!QAZXSW@");

		Jedis jedis = pool.getResource();
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		System.out.println(value);
	}
}
