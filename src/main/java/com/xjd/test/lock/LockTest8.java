package com.xjd.test.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author elvis.xu
 * @since 2017-10-15 12:25
 */
public class LockTest8 {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);
		latch.countDown();
		latch.countDown();
		latch.countDown();
		latch.countDown();
	}
}
