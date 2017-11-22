package com.xjd.test.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author elvis.xu
 * @since 2017-10-11 14:50
 */
public class LockTest4 {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		countDownLatch.countDown();

		countDownLatch.await();

		System.out.println("OK");
	}
}
