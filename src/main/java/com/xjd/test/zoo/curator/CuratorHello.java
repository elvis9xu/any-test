package com.xjd.test.zoo.curator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;


/**
 * @author elvis.xu
 * @since 2017-06-27 11:04
 */
public class CuratorHello {
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

		client.create().forPath("test1");

		client.create().forPath("test2", "init".getBytes());

		client.create().withMode(CreateMode.PERSISTENT).forPath("test3");

		client.create().withMode(CreateMode.EPHEMERAL).forPath("test4", "init".getBytes());

		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("test/test1/test1");

		client.delete().forPath("test1");

		client.delete().guaranteed().forPath("test2");

		byte[] test3s = client.getData().forPath("test3");
		System.out.println(new String(test3s));

		Stat stat = new Stat();
		byte[] test4s = client.getData().storingStatIn(stat).forPath("test3");
		System.out.println(new String(test4s));
		System.out.println(stat);

		client.setData().forPath("test3", "haha".getBytes());

		client.checkExists().forPath("test4");

		List<String> children = client.getChildren().forPath("test");
		System.out.println(Arrays.toString(children.toArray()));

		client.inTransaction().check().forPath("test5")
				.and()
				.create().withMode(CreateMode.EPHEMERAL).forPath("test5", "data".getBytes())
				.and()
				.setData().withVersion(1).forPath("test5", "data2".getBytes())
				.and()
				.commit();

//		client.inTransaction().check().forPath("test5")
//				.and()
//				.create().withMode(CreateMode.EPHEMERAL).forPath("test5", "data".getBytes())
//				.and()
//				.setData().withVersion(1).forPath("test5", "data2".getBytes())
//				.and()
//				.commit();

		Executor executor = Executors.newFixedThreadPool(2);
		client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL)
				.inBackground((c, event) -> {
					System.out.println(event);
				}, executor)
				.forPath("test/test2");


		client.close();
	}
}
