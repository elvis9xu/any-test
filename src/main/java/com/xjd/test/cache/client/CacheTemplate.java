package com.xjd.test.cache.client;


import java.util.HashMap;
import java.util.UUID;

import org.apache.log4j.Logger;


/**
 * Cache的模板类.
 *
 * @author <a href="mailto:liqun.tang@chinapnr.com">唐力群</a>
 */
public class CacheTemplate {
    public static final int DEFAULT_EXP_SECONDS = 86400; // 默认超时秒数，一天

	private static final Logger log = Logger.getLogger(CacheTemplate.class.getName());

    private CPCacheClient cacheClient;

    private static final ThreadLocal<HashMap<String, Object>> preReadCacheMap = new ThreadLocal<HashMap<String, Object>>();

    public String getCacheKey(String baseKey, int exp) {
        return getCache(baseKey, exp, new CacheCallback<String>() {
            @Override
            public String loadData() {
                return UUID.randomUUID().toString();
            }
        });
    }

    public String getCacheKey(String baseKey) {
        return getCacheKey(baseKey, DEFAULT_EXP_SECONDS);
    }

    @SuppressWarnings("unchecked")
    public <T> T getCache(String key, int exp, CacheCallback<T> callback) {
        if (cacheClient == null) {
            return null;
        }

        T t = null;
        // 尝试使用mget得到的值.
        Object o = getFromPreReadMap(key);
        if (o != null) {
            try {
                t = (T) o;
            } catch (Exception e) {
                log.warn("Get Key[" + key + "] from preReadMap Error.", e);
            }
            if (t != null) {
                return t;
            }
        }

        try {
            t = (T) cacheClient.getObject(key);
        } catch (Exception e) {
            log.warn("When get cache[key:" + key + "] found exception.", e);
            delete(key);
        }

        if (t == null) {
            synchronized (this) {
                t = (T) getFromPreReadMap(key);
                if (t != null) {
                    return t;
                }

                t = callback.loadData();
                if (t != null) {
                    try {
                        cacheClient.putObject(key, t, exp);
                        // 加到预读Map中，同一线程下次读取时会成为本地内存读取，速度更快
                        addToPreReadMap(key, t);
                    } catch (Exception e) {
                        log.warn("When put cache[key:" + key + "] found exception.", e);
                        e.printStackTrace();
                    }
                }
            }
        }

        return t;
    }

    public <T> T getCache(String key, CacheCallback<T> callback) {
        return getCache(key, 120, callback);
    }

    public void setCacheClient(CPCacheClient cacheClient) {
        this.cacheClient = cacheClient;
    }

    public CPCacheClient getCacheClient() {
        return cacheClient;
    }

    /**
     * 从缓存中删除key对应的值.
     *
     * @param key
     */
    public void delete(String key) {
        if (cacheClient == null) {
            return;
        }

        try {
            cacheClient.removeObject(key);
            deleteToPreReadMap(key);
        } catch (Exception e1) {
            log.warn("When delete cache[key:" + key + "] found exception.", e1);
        }
    }

    /**
     * 在缓存中保存key对应的value值.
     *
     * @param key
     * @param exp
     * @param value
     */
    public void set(String key, int exp, Object value) {
        if (cacheClient == null || value == null) {
            return;
        }

        try {
            cacheClient.putObject(key, value, exp);
            // 加到预读Map中，同一线程下次读取时会成为本地内存读取，速度更快
            addToPreReadMap(key, value);
        } catch (Exception e) {
            log.warn("When set cache[key:" + key + "] found exception.", e);
            delete(key);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        if (cacheClient == null) {
            return null;
        }

        T t = null;
        // 尝试使用mget得到的值.
        Object o = getFromPreReadMap(key);
        if (o != null) {
            try {
                t = (T) o;
            } catch (Exception e) {
                log.warn("Get Key[" + key + "] from preReadMap Error.", e);
            }
            if (t != null) {
                return t;
            }
        }
        try {
            return (T) cacheClient.getObject(key);
        } catch (Exception e) {
            log.warn("When get cache[key:" + key + "] found exception.", e);
            return null;
        }
    }

    /**
     * 从预读列表中查询key对应的值.
     *
     * @param key
     * @return
     */
    protected Object getFromPreReadMap(String key) {
        if (isPreReadCache() && preReadCacheMap.get() != null) {
            return preReadCacheMap.get().get(key);
        }
        return null;
    }

    /**
     * 把值加到预读Map中.
     *
     * @param key
     * @param value
     */
    protected void addToPreReadMap(String key, Object value) {
        if (isPreReadCache()) {
            if (preReadCacheMap.get() == null) {
                preReadCacheMap.set(new HashMap<String, Object>());
            }
            preReadCacheMap.get().put(key, value);
        }
    }

    /**
     * 从预读Map中删除值.
     *
     * @param key
     */
    protected void deleteToPreReadMap(String key) {
        if (isPreReadCache()) {
            if (preReadCacheMap.get() == null) {
                preReadCacheMap.set(new HashMap<String, Object>());
            }
            preReadCacheMap.get().remove(key);
        }
    }

    public static boolean isPreReadCache() {
        return false;
    }

}
