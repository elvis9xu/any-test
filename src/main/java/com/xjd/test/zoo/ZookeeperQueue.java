package com.xjd.test.zoo;

import java.io.IOException;

import org.apache.zookeeper.*;

/**
 * @author elvis.xu
 * @since 2017-06-12 10:24
 */
public class ZookeeperQueue {
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("service4:2181,service4:2182", 30000, new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {
				System.out.println("事件: " + watchedEvent);
			}
		});

//		if (zk.getChildren("/queue", true).size() == 0) {
//			zk.create("/queue/ele", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
//		}
	}

}
