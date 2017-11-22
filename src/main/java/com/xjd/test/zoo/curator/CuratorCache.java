package com.xjd.test.zoo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;


/**
 * @author elvis.xu
 * @since 2017-06-27 11:04
 */
public class CuratorCache {
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

		PathChildrenCache cache = new PathChildrenCache(client, "/curator", true);
		cache.start();

		PathChildrenCacheListener listener = (curatorFramework, pathChildrenCacheEvent) -> {
			System.out.println(pathChildrenCacheEvent.getType());
			System.out.println(pathChildrenCacheEvent.getData());
		};

		cache.getListenable().addListener(listener);

		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/curator/test10");
		Thread.sleep(1000);

		client.close();
	}
}
