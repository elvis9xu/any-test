package com.xjd.test.zoo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.xjd.distributed.lock.zoo.ZooDistributedLock;
import com.xjd.distributed.lock.zoo.ZooDistributedLocker;

/**
 * @author elvis.xu
 * @since 2017-11-02 18:39
 */
public class DistributedLockTest {
	public static void main(String[] args) {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

//		CuratorFramework client = CuratorFrameworkFactory.newClient("service4:2181,service4:2182", 5000, 3000, retryPolicy);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("service4:2181,service4:2181")
				.connectionTimeoutMs(3000)
				.sessionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
//				.namespace("curator")
				.build();

		client.start();

		ZooDistributedLocker locker = new ZooDistributedLocker("/lockPath2", client);
		locker.start();
		ZooDistributedLock lock = locker.getLock("lock1", 60000L, 1, 10);
		for (int t = 0; t < 5; t++) {

		long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			lock.lock();
//			// do things
//			lock.unlock();
		}
		for (int i = 0; i < 100; i++) {
//			lock.lock();
//			// do things
			lock.unlock();
		}
		long cost = System.currentTimeMillis() - start;
		System.out.println("total cost: " + cost);

		}

		locker.close();
		client.close();
	}
}
