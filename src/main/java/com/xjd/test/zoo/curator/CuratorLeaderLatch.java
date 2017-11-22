package com.xjd.test.zoo.curator;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author elvis.xu
 * @since 2017-06-27 15:20
 */
public class CuratorLeaderLatch {
	public static void main(String[] args) throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

//		CuratorFramework client = CuratorFrameworkFactory.newClient("service4:2181,service4:2182", 5000, 3000, retryPolicy);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("service4:2181,service4:2182")
				.connectionTimeoutMs(3000)
				.sessionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
				.namespace("curator")
				.build();
		client.start();

		List<LeaderLatch> list = new ArrayList<>();
		for (int i = 0; i < 1; i++) {

			String path = "/leader/latch";
			LeaderLatch latch = new LeaderLatch(client, path, "CLIENT #" + i);

			final int t = i;
			latch.addListener(new LeaderLatchListener() {
				@Override
				public void isLeader() {
					System.out.println(t + " is leader");
				}

				@Override
				public void notLeader() {
					System.out.println(t + " not leader");
				}
			});

			latch.start();
			list.add(latch);
		}


		new BufferedReader(new InputStreamReader(System.in)).readLine();

		list.forEach(e -> {
			if (e.hasLeadership()) {
				System.out.println("current leader is : " + e);
				try {
					e.close(LeaderLatch.CloseMode.NOTIFY_LEADER);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		new BufferedReader(new InputStreamReader(System.in)).readLine();

		list.forEach(e -> {
			try {
				e.close(LeaderLatch.CloseMode.NOTIFY_LEADER);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		new BufferedReader(new InputStreamReader(System.in)).readLine();

		client.close();

	}
}
