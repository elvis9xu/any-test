package com.xjd.test.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

public class GuavaCachePerf {
    public static void main(String[] args) throws ExecutionException {
//        write();
        read();
    }

    public static void write() {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            cache.put(i + "", i + "");
        }
        long end = System.currentTimeMillis();
        System.out.println("write 1000000: " + (end - start) + "ms");
    }

    /**
     * write 1000000: 434ms
     * read  1000000: 946ms
     * total  1000000: 1380ms
     *
     *
     * write 1000000: 442ms
     * read  1000000: 946ms
     * total  1000000: 1388ms
     *
     *
     * write 1000000: 455ms
     * read  1000000: 949ms
     * total  1000000: 1404ms
     *
     * write 1000000: 459ms
     * read  1000000: 935ms
     * total  1000000: 1394ms
     *
     * write 1000000: 457ms
     * read  1000000: 942ms
     * total  1000000: 1399ms
     *
     * avg write : 449ms
     * avg read: 943ms
     * avg total: 1392ms
     * @throws ExecutionException
     */
    public static void read() throws ExecutionException {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            cache.put(i + "", i + "");
        }
        long end = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            cache.get(i + "", () -> "");
        }
        long end2 = System.currentTimeMillis();
        System.out.println("write 1000000: " + (end - start) + "ms");
        System.out.println("read  1000000: " + (end2 - end) + "ms");
        System.out.println("total  1000000: " + (end2 - start) + "ms");
    }
}
