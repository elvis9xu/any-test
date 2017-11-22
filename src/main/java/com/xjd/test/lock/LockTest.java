package com.xjd.test.lock;

import java.util.concurrent.locks.Lock;

import org.jboss.netty.util.internal.NonReentrantLock;

/**
 * @author elvis.xu
 * @since 2017-09-01 16:35
 */
public class LockTest {
	public static void main(String[] args) {
		Lock lock = new NonReentrantLock();

		lock.lock();
		lock.lock();

	}
}
