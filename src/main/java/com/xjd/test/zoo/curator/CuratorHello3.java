package com.xjd.test.zoo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.nodes.PersistentTtlNode;
import org.apache.curator.retry.ExponentialBackoffRetry;


/**
 * @author elvis.xu
 * @since 2017-06-27 11:04
 */
public class CuratorHello3 {
	public static void main(String[] args) throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

//		CuratorFramework client = CuratorFrameworkFactory.newClient("service4:2181,service4:2182", 5000, 3000, retryPolicy);
		CuratorFramework client = CuratorFrameworkFactory.builder()
//				.connectString("service4:2181,service4:2182")
				.connectString("service4:2181")
				.connectionTimeoutMs(3000)
				.sessionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
				.namespace("curator2")
				.build();

		client.start();

		PersistentTtlNode persistentTtlNode = new PersistentTtlNode(client, "/test3", 20000L, "X".getBytes());
		persistentTtlNode.start();
		for (int i = 0; i < 600; i++) {
			Thread.sleep(1000L);
		}
//		persistentTtlNode.close();

		client.close();
	}
}
