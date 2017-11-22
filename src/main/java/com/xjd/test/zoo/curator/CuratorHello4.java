package com.xjd.test.zoo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;


/**
 * @author elvis.xu
 * @since 2017-06-27 11:04
 */
public class CuratorHello4 {
	public static void main(String[] args) throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

//		CuratorFramework client = CuratorFrameworkFactory.newClient("service4:2181,service4:2182", 5000, 3000, retryPolicy);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("127.0.0.1:2181")
				.connectionTimeoutMs(3000)
				.sessionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
//				.namespace("test")
				.build();

		client.start();

//		List<CuratorTransactionResult> results = client.transaction().forOperations(
//				client.transactionOp().create().withTtl(20000L).withMode(CreateMode.PERSISTENT_SEQUENTIAL_WITH_TTL).forPath("/test/test2"),
//				client.transactionOp().setData().withVersion(6).forPath("/test", "HAHA".getBytes(Charset.forName("UTF-8")))
//		);
//
//		for (CuratorTransactionResult result : results) {
//			System.out.println(JsonUtils.toJson(result));
//		}
//
//		String s = client.create().withTtl(1000L).withMode(CreateMode.PERSISTENT_SEQUENTIAL_WITH_TTL).forPath("/test/test2");
//		System.out.println(s);

//		TreeCache treeCache = new TreeCache(client, "/test/test2");
//		treeCache.getListenable().addListener((client1, event) -> {
//			System.out.println(event);
//		});
//		treeCache.start();

		client.delete().forPath("/test/test2/10000000002");

		for (int i = 0; i < 600; i++) {
			Thread.sleep(1000L);
		}

		client.close();
	}
}
