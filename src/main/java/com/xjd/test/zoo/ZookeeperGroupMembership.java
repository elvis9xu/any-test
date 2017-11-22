package com.xjd.test.zoo;

import java.io.IOException;

import org.apache.zookeeper.*;

/**
 * @author elvis.xu
 * @since 2017-06-12 09:01
 */
public class ZookeeperGroupMembership {
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("service4:2181,service4:2182", 30000, new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {
				System.out.println("事件: " + watchedEvent);
			}
		});

		zk.getChildren("/gm", true);

		zk.create("/gm/mem", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

		Thread.sleep(30000L);

		zk.close();
	}
}
