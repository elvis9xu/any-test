package com.xjd.test.zoo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.zookeeper.*;

/**
 * @author elvis.xu
 * @since 2017-06-09 16:16
 */
public class ZookeeperHello {
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("service4:2181,service4:2182", 30000, new Watcher() {
			@Override
			public void process(WatchedEvent watchedEvent) {
				System.out.println("事件: " + watchedEvent);
			}
		});

		//
		String s = zk.create("/zk_test", "MYDATA".getBytes(Charset.forName("utf8")), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		String s1 = zk.create("/zk_test/zk_child1", "CHILD".getBytes(Charset.forName("utf8")), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(s);
		System.out.println(s1);

		byte[] data = zk.getData("/zk_test", false, null);
		System.out.println(new String(data, "utf8"));
		data = zk.getData("/zk_test/zk_child1", false, null);
		System.out.println(new String(data, "utf8"));

		List<String> children = zk.getChildren("/zk_test", false);
		children.forEach(e -> {
			System.out.println(e);
		});

		zk.delete("/zk_test/zk_child1", -1);
		zk.delete("/zk_test", -1);

		zk.close();

	}
}
