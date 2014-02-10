package com.xjd.test.cache.client;

/**
 * cache回调接口.
 */
public interface CacheCallback<T> {
    T loadData();
}
