package com.xjd.test.cache.client.impl;

import java.io.IOException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;

import com.xjd.test.cache.client.CPCacheClient;

/**
 * @author su.zhang
 * @version $Id: CPCacheClient.java, v 0.1 2012-8-21 下午05:03:47 Administrator Exp $
 * 
 * example:
 // Get a memcached client connected to several servers
MemcachedClient c=new MemcachedClient(
        AddrUtil.getAddresses("server1:11211 server2:11211"));

// Try to get a value, for up to 5 seconds, and cancel if it doesn't return
Object myObj=null;
Future<Object> f=c.asyncGet("someKey");
try {
    myObj=f.get(5, TimeUnit.SECONDS);
} catch(TimeoutException e) {
    // Since we don't need this, go ahead and cancel the operation.  This
    // is not strictly necessary, but it'll save some work on the server.
    f.cancel(false);
    // Do other timeout related stuff
}
 * 
 */
public class CPCacheClientImpl implements CPCacheClient {

    private static final Logger logger = Logger.getLogger(CPCacheClientImpl.class);

    private String              defaultExpiredTime;
    private MemcachedClient     memcachedClient;

    public CPCacheClientImpl() {
    }

    public CPCacheClientImpl(String cpAddress) {
    	try {
            memcachedClient = new MemcachedClient(AddrUtil.getAddresses(cpAddress));
        } catch (Exception e) {
            logger.error("【分布式缓存】分布式缓存初始化失败,集群地址:" + cpAddress);
            throw new RuntimeException(e);
        }
    }

    public void removeObject(String key) {
    	removeObject(key, 0);
    }
    
    public void removeObject(String key, int i) {
    	try {
	        memcachedClient.delete(key);
    	} catch(Exception e) {
    		if(i == 3) throw new RuntimeException(e);
    		i ++;
    		this.removeObject(key, i);
    	}
    }

    public Object getObject(String key) {
    	return getObject(key, 0);
    }
    
    public Object getObject(String key, int i) {
    	try {
	        return memcachedClient.get(key);
    	} catch(Exception e) {
    		if(i == 3) throw new RuntimeException(e);
    		i ++;
    		return this.getObject(key, i);
    	}
    }

    public void putObject(String key, Object data, int exp) {
    	this.putObject(key, data, exp, 0);
    }
    
    public void putObject(String key, Object data, int exp, int i) {
    	try {
	        memcachedClient.set(key, exp, data);
    	} catch(Exception e) {
    		if(i == 3) throw new RuntimeException(e);
    		i ++;
    		this.putObject(key, data, exp, i);
    	}
    }

    public void putObject(String key, Object data) {
        putObject(key, data, Integer.valueOf(defaultExpiredTime));
    }

    public void setDefaultExpiredTime(String defaultExpiredTime) {
        this.defaultExpiredTime = defaultExpiredTime;
    }
    
    /**
     * Setter method for property <tt>cpAddress</tt>.
     * 
     * @param cpAddress value to be assigned to property cpAddress
     */
    public void setCpAddress(String cpAddress) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("【分布式缓存】分布式缓存初始化，集群地址:" + cpAddress);
            }
            memcachedClient = new MemcachedClient(AddrUtil.getAddresses(cpAddress));
        } catch (IOException e) {
            logger.error("【分布式缓存】分布式缓存初始化失败,集群地址:" + cpAddress);
            throw new RuntimeException(e);
        }
    }
}
