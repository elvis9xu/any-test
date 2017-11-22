package com.xjd.test.zoo;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author elvis.xu
 * @since 2017-06-09 17:50
 */
public class ZookeeperConfigurationManagement {
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("service4:2181,service4:2182", 30000, new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {
				System.out.println("事件: " + watchedEvent);
			}
		});

		zk.register(new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				zk.register(this);
				System.out.println("事件0: " + event);
			}
		});

//		String s = zk.create("/cm", "Configuration Management Root".getBytes(Charset.forName("utf8")), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

		zk.exists("/cm/config", new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				// 继续监听
				try {
					zk.exists("/cm/config", this);
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 处理监听事务
				System.out.println("事件2: " + event);
			}
		});


		Thread.sleep(30000L);

//		zk.delete("/cm", -1);

		zk.close();
	}
}
