package com.xjd.test.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author elvis.xu
 * @since 2017-09-01 19:18
 */
public class LockTest2 {
	public static void main(String[] args) {

		ReadWriteLock lock = new ReentrantReadWriteLock();

		lock.writeLock().lock();
		System.out.println("write");
		lock.readLock().lock();
		System.out.println("read");
	}
}
