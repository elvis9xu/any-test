package com.xjd.test.redis;

import redis.clients.jedis.Jedis;

/**
 * @author elvis.xu
 * @since 2017-11-27 18:23
 */
public class JedisTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("service4", 6380);
		jedis.auth("!QAZXSW@");
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		System.out.println(value);
	}
}
