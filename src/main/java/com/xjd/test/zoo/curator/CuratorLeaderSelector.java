package com.xjd.test.zoo.curator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author elvis.xu
 * @since 2017-07-03 11:01
 */
public class CuratorLeaderSelector {
	public static void main(String[] args) throws InterruptedException, IOException {

		List<LeaderSelector> list = new ArrayList<>(10);
		List<CuratorFramework> list2 = new ArrayList<>(10);
		for (int i = 0; i < 2; i++) {
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
			list2.add(client);

			final int t = i;
			LeaderSelector leaderSelector = new LeaderSelector(client, "/leaderSelector", new LeaderSelectorListenerAdapter() {
				@Override
				public void takeLeadership(CuratorFramework client) throws Exception {
					System.out.println("take leadership: " + t);

					Thread.sleep(2000L);
				}
			});

//			leaderSelector.autoRequeue();
			leaderSelector.start();
			list.add(leaderSelector);
		}

		new BufferedReader(new InputStreamReader(System.in)).readLine();

		list.forEach(e -> {
			e.close();
		});
		list2.forEach(e -> {
			e.close();
		});
	}
}
