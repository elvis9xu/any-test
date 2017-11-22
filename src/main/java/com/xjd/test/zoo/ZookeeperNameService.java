package com.xjd.test.zoo;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.zookeeper.*;

/**
 * @author elvis.xu
 * @since 2017-06-09 17:50
 */
public class ZookeeperNameService {
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("service4:2181,service4:2182", 30000, new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {
				System.out.println("事件: " + watchedEvent);
			}
		});

		String s = zk.create("/ns", "Name Service Root".getBytes(Charset.forName("utf8")), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(s);
		s = zk.create("/ns/service", "service".getBytes(Charset.forName("utf8")), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println(s);
		s = zk.create("/ns/service", "service".getBytes(Charset.forName("utf8")), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println(s);
		s = zk.create("/ns/service", "service".getBytes(Charset.forName("utf8")), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println(s);

		zk.delete("/ns", -1);

		zk.close();
	}
}
