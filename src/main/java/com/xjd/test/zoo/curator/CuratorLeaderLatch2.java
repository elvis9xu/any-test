package com.xjd.test.zoo.curator;


import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author elvis.xu
 * @since 2017-06-27 15:20
 */
public class CuratorLeaderLatch2 {
	public static void main(String[] args) throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

//		CuratorFramework client = CuratorFrameworkFactory.newClient("service4:2181,service4:2182", 5000, 3000, retryPolicy);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("service4:2181,service4:2182")
				.connectionTimeoutMs(3000)
				.sessionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
				.namespace("curator/leader")
				.build();
		client.start();


		String path = "/latch";
		LeaderLatch latch = new LeaderLatch(client, path, "CLIENT #");
		latch.start();

		latch.await(5L, TimeUnit.SECONDS);

		System.out.println(latch.hasLeadership());

		latch.close();

		client.close();
	}
}
