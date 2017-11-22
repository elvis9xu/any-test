package com.xjd.test.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author elvis.xu
 * @since 2017-10-13 09:47
 */
public class LockTest5 {
	public static void main(String[] args) throws InterruptedException {
		final Lock lock = new ReentrantLock();

		Thread t = new Thread() {
			@Override
			public void run() {
				lock.lock();
				System.out.println("Thread get lock.");
			}

			@Override
			protected void finalize() throws Throwable {
				System.out.println("Thread finalized.");
			}
		};
		t.start();
		t.join();
		t = null;
		System.gc();

		Thread.sleep(5000L);
		System.out.println("The thread should be gc.");
		lock.lock();
		System.out.println("The main thread get lock.");
		lock.unlock();
	}
}
