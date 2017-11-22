package com.xjd.test.zoo;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * @author elvis.xu
 * @since 2017-06-12 09:14
 */
public class ZookeeperLocks {
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

		ZooKeeper zk = new ZooKeeper("service4:2181,service4:2182", 30000, null);

		String s = zk.create("/locks/lock", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println(s);
		String[] fs = new String[1];

		zk.register(new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {
				System.out.println("事件: " + watchedEvent);

				if (watchedEvent.getType() == Event.EventType.NodeDeleted && watchedEvent.getPath().equals(fs[0])) {
					try {
						lock(zk, s);
					} catch (KeeperException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		fs[0] = lock(zk, s);

		Thread.sleep(60000L);
	}

	public static String lock(ZooKeeper zk, String s) throws KeeperException, InterruptedException {
		while(true) {
			List<String> children = zk.getChildren("/locks", false);

			String[] fl = new String[2];
			children.stream().filter(e -> ("/locks/" + e).compareTo(s) < 0).sorted().forEachOrdered(e -> {
				if (fl[0] == null) {
					fl[0] = "/locks/" + e;
				}
				fl[1] = "/locks/" + e;
			});

			if (fl[0] == null || s.equalsIgnoreCase(fl[0])) {
				// 获得锁
				System.out.println("获取成功!");
				return null;
			} else {
				// 监控前一个node
				Stat exists = zk.exists(fl[1], true);
				if (exists != null) {
					// 等待锁
					System.out.println("等待锁...");
					return fl[1];
				} else {
					// 重新获取
					System.out.println("前锁不存在,重新获取...");
					continue;
				}
			}
		}
	}
}
