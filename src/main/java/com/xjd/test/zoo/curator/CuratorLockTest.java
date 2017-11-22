package com.xjd.test.zoo.curator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author elvis.xu
 * @since 2017-10-09 17:46
 */
public class CuratorLockTest {
	public static void main(String[] args) throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

//		CuratorFramework client = CuratorFrameworkFactory.newClient("service4:2181,service4:2182", 5000, 3000, retryPolicy);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("service4:2181,service4:2182")
				.connectionTimeoutMs(3000)
				.sessionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
//				.namespace("curator")
				.build();

		client.start();

//		client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test/locktest/lock", "HELLO".getBytes());
		String s = client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/test/locktest/lock", "HELLO".getBytes());
		System.out.println(s);

		String i = null;
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in, Charset.forName("utf8")));
		while ( !(i = r.readLine()).equals("-1") ) {
			System.out.println(i);
		}

		client.close();
	}
}
