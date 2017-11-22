package com.xjd.test.zoo.curatorapply;

import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author elvis.xu
 * @since 2017-11-02 18:39
 */
public class InterProcessMutexTest {
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

		InterProcessMutex lock = new InterProcessMutex(client, "/lockPath");
		try {
			if (lock.acquire(3, TimeUnit.SECONDS)) {
				try {
					// do things
				} finally {
					lock.release();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.close();
	}
}
