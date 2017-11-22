package com.xjd.test.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author elvis.xu
 * @since 2017-10-13 09:47
 */
public class LockTest6 {
	public static void main(String[] args) throws InterruptedException {
		final Lock lock = new ReentrantLock();

		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("Thread getting lock.");
					lock.lockInterruptibly();
					lock.unlock();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			protected void finalize() throws Throwable {
				System.out.println("Thread finalized.");
			}
		};

		lock.lock();

		t.setDaemon(true);
		t.start();

		Thread.sleep(2000L);

		t.interrupt();

		Thread.sleep(2000L);

		lock.unlock();
	}
}
