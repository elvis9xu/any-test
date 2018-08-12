package com.xjd.test.cache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.cache.CacheProperties;

public class CaffeinePerf {
    public static void main(String[] args) {
//        write();
        read();
    }

    public static void write() {
        Cache<String, String> cache = Caffeine.newBuilder().build();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            cache.put(i + "", i + "");
        }
        long end = System.currentTimeMillis();
        System.out.println("write 1000000: " + (end - start) + "ms");
    }


    /**
     * write 1000000: 277ms
     * read  1000000: 94ms
     * total  1000000: 371ms
     *
     * write 1000000: 268ms
     * read  1000000: 97ms
     * total  1000000: 365ms
     *
     * write 1000000: 267ms
     * read  1000000: 94ms
     * total  1000000: 361ms
     *
     * write 1000000: 283ms
     * read  1000000: 94ms
     * total  1000000: 377ms
     *
     * write 1000000: 271ms
     * read  1000000: 97ms
     * total  1000000: 368ms
     *
     * avg write: 273ms  -->guava U 40%
     * avg read: 95ms    -->guava U 90%
     * avg total: 368ms  -->guava U 74%
     */
    public static void read() {
        Cache<String, String> cache = Caffeine.newBuilder().build();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            cache.put(i + "", i + "");
        }
        long end = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            cache.get(i + "", v -> v);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("write 1000000: " + (end - start) + "ms");
        System.out.println("read  1000000: " + (end2 - end) + "ms");
        System.out.println("total  1000000: " + (end2 - start) + "ms");
    }
}
