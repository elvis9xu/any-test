package com.xjd.test.zoo.curator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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
public class CuratorHello5 {
	public static void main(String[] args) throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

//		CuratorFramework client = CuratorFrameworkFactory.newClient("service4:2181,service4:2182", 5000, 3000, retryPolicy);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("service4:2181,service4:2181")
				.connectionTimeoutMs(3000)
				.sessionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
//				.namespace("test2")
				.build();

		client.start();

//		Stat stat = new Stat();
//		String s = client.create().storingStatIn(stat).creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/test2/locker2");
//		System.out.println(stat);

		TreeCache treeCache = new TreeCache(client, "/test2/locker2");
		treeCache.getListenable().addListener(new TreeCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
				System.out.println(event);
			}
		});
		treeCache.start();

//		PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/test2/locker1", true, false, Executors.newCachedThreadPool());
//		pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
//			@Override
//			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
//				System.out.println(event);
//			}
//		});
//		pathChildrenCache.start();

//		List<String> children = client.getChildren().inBackground(new BackgroundCallback() {
//			@Override
//			public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
//				System.out.println(event);
//			}
//		}).forPath("/");



		String i = null;
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in, Charset.forName("utf8")));
		while ( !(i = r.readLine()).equals("-1") ) {
			System.out.println(i);
		}

		client.close();
	}
}
