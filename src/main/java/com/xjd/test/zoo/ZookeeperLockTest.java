package com.xjd.test.zoo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.zookeeper.*;

/**
 * @author elvis.xu
 * @since 2017-10-09 16:57
 */
public class ZookeeperLockTest {
	public static void main(String[] args) throws Exception {
		ZooKeeper zk = new ZooKeeper("service4:2181,service4:2181", 30000, new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {
				System.out.println("事件: " + watchedEvent);
			}
		});

		Watcher watcher = new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println("事件/test/locktest: " + event);
				try {
					zk.exists("/test/locktest", this);
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Watcher watcher2 = new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println("事件2/test/locktest: " + event);
				try {
					zk.getChildren("/test/locktest", this);
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		zk.exists("/test/locktest", watcher);
		zk.getChildren("/test/locktest", watcher2);

//		zk.removeAllWatches("/test/locktest", Watcher.WatcherType.Any, true);

		//
		String s = zk.create("/test/locktest/lock", "MYDATA".getBytes(Charset.forName("utf8")), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

		String i = null;
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in, Charset.forName("utf8")));
		while ( !(i = r.readLine()).equals("-1") ) {
			System.out.println(i);
		}
		zk.close();
		System.out.println("closed");
	}
}
