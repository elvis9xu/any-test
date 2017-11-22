package com.xjd.test.zoo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;


/**
 * @author elvis.xu
 * @since 2017-06-27 11:04
 */
public class CuratorHello2 {
	public static void main(String[] args) throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

//		CuratorFramework client = CuratorFrameworkFactory.newClient("service4:2181,service4:2182", 5000, 3000, retryPolicy);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("service4:2181,service4:2182")
				.connectionTimeoutMs(3000)
				.sessionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
				.namespace("curator2")
				.build();

		client.start();


		TreeCache treeCache = new TreeCache(client, "/test");
		treeCache.getListenable().addListener(new TreeCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
				System.out.println("TreeCache: " + event);
			}
		});
		treeCache.start();



		TreeCache treeCache2 = new TreeCache(client, "/test");
		treeCache2.getListenable().addListener(new TreeCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
				System.out.println("TreeCache2: " + event);
			}
		});
		treeCache2.start();


		for (int i = 0; i < 600; i++) {
			Thread.sleep(1000L);
		}

		treeCache.close();
		treeCache2.close();
		client.close();
	}
}
