package com.xjd.test.cache.client.impl;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;

import com.xjd.test.cache.client.CPCacheClient;

public class CPMasterSlaveCacheClientImpl implements CPCacheClient {
	
	private static final Logger logger = Logger.getLogger(CPMasterSlaveCacheClientImpl.class);
	private MemcachedClient masterMemcachedClient;
	private MemcachedClient slaveMemcachedClient;
	private Integer exipredTime;

	public CPMasterSlaveCacheClientImpl(String masterAddresses,
			String slaveAddresses, Integer exipredTime) {
		this.exipredTime = exipredTime;
		try {
			this.masterMemcachedClient = new MemcachedClient(AddrUtil.getAddresses(masterAddresses));
			this.slaveMemcachedClient = new MemcachedClient(AddrUtil.getAddresses(slaveAddresses));
		} catch (Exception e) {
			logger.error("【分布式缓存】缓存客户端初始化失败,主集群:" + masterAddresses + " 备集群:" + slaveAddresses, e);
			throw new RuntimeException(e);
		}
	}
	
	public Object getObject(String key) {
		return this.getObject(key, 0);
	}

	public Object getObject(String key, int i) {
		try {
			Object masterValue = this.masterMemcachedClient.get(key);
			if (null == masterValue) {
				return slaveAsyncGet(key);
			}
			this.slaveMemcachedClient.set(key, this.exipredTime.intValue(), masterValue);
			return masterValue;
		} catch (Exception e) {
			if(i == 3) throw new RuntimeException(e);
    		i ++;
			return this.getObject(key, i);
		}
	}

	private Object slaveAsyncGet(String key) {
		Object slaveValue = this.slaveMemcachedClient.get(key);
		if (null != slaveValue) {
			this.masterMemcachedClient.set(key, this.exipredTime.intValue(), slaveValue);
		}
		return slaveValue;
	}
	
	public void removeObject(String key) {
		this.removeObject(key, 0);
	}

	public void removeObject(String key, int i) {
		try {
			this.masterMemcachedClient.delete(key);
			this.slaveMemcachedClient.delete(key);
		} catch (Exception e) {
			if(i == 3) throw new RuntimeException(e);
    		i ++;
			this.removeObject(key, i);
		}
	}
	
	public void putObject(String key, Object data, int exp) {
		this.putObject(key, data, exp, 0);
	}

	public void putObject(String key, Object data, int exp, int i) {
		try {
			this.masterMemcachedClient.set(key, exp, data);
			this.slaveMemcachedClient.set(key, exp, data);
		} catch (Exception e) {
			if(i == 3) throw new RuntimeException(e);
    		i ++;
			this.putObject(key, data, exp, i);
		}
	}

	public void putObject(String key, Object data) {
		putObject(key, data, this.exipredTime.intValue());
	}
}