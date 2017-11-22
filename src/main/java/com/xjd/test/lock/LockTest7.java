package com.xjd.test.lock;

import java.util.concurrent.locks.*;

/**
 * @author elvis.xu
 * @since 2017-10-13 16:13
 */
public class LockTest7 {
	public static void main(String[] args) throws InterruptedException {
		ReadWriteLock lock = new ReentrantReadWriteLock();
		Condition c1 = lock.writeLock().newCondition();
//		Condition c2 = lock.readLock().newCondition();

		Thread t = new Thread(){
			@Override
			public void run() {
				lock.writeLock().lock();
				System.out.println("locked");
				try {
					c1.await();
				} catch (InterruptedException e) {
					System.out.println("interrupted");
				}
//				System.out.println("sleep");
//				try {
//					Thread.sleep(5000L);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				lock.writeLock().unlock();
				System.out.println("unlocked");
			}
		};

		t.setDaemon(true);
		t.start();

		Thread.sleep(2000L);

//		Thread.sleep(1000L);
		lock.writeLock().lock();
		System.out.println("main");
		c1.signalAll();
		t.interrupt();
		Thread.sleep(1000L);
		System.out.println("main sleep");
		lock.writeLock().unlock();
		t.join();
	}
}
