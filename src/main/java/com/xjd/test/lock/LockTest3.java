package com.xjd.test.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author elvis.xu
 * @since 2017-09-04 16:34
 */
public class LockTest3 {
	public static void main(String[] args) throws InterruptedException {
		ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		final Condition condition = readWriteLock.writeLock().newCondition();

		new Thread() {
			@Override
			public void run() {
				readWriteLock.writeLock().lock();
				try {
					System.out.println("begin wait....");
					condition.await();
					System.out.println("wait ok!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				readWriteLock.writeLock().unlock();
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				readWriteLock.writeLock().lock();
				condition.signal();
				System.out.println("signal ok!");
				readWriteLock.writeLock().unlock();
			}
		}.start();


		Thread.sleep(10000L);

	}


}
