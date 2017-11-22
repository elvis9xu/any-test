package com.xjd.test.zoo.curatorapply;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author elvis.xu
 * @since 2017-11-02 18:33
 */
public class LeaderLatchTest {
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

		LeaderLatch leaderLatch = new LeaderLatch(client, "/leaderLatch", "ID");
		leaderLatch.addListener(new LeaderLatchListener() {
			@Override
			public void isLeader() {
				// do things
				System.out.println("got leader2");
			}

			@Override
			public void notLeader() {
				// do things
				System.out.println("lost leader");
			}
		});
		try {
			leaderLatch.start();
			leaderLatch.await(1L, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (leaderLatch.hasLeadership()) {
			// do things
			System.out.println("got leader1");
		}
		try {
			leaderLatch.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		client.close();
	}
}
