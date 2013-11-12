package com.xjd.test.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiServerSocketListenSamePort {

	/**
	 * <pre>
	 * 两个ServerSocket监听同一个端口时, 只有当第一个监听的ServerSocket关闭后, 第二个监听端口才能投入使用, 相当于替补
	 * </pre>
	 * @author elvis.xu
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Selector sel = Selector.open();
		
		InetSocketAddress addr = new InetSocketAddress(9007);
		
		ServerSocketChannel ssc1 = ServerSocketChannel.open();
		ssc1.configureBlocking(false);
		ssc1.socket().setReuseAddress(true);
		ssc1.socket().bind(addr, 1);
		ssc1.register(sel, SelectionKey.OP_ACCEPT).attach("SSC1");
		
		ServerSocketChannel ssc2 = ServerSocketChannel.open();
		ssc2.configureBlocking(false);
		ssc2.socket().setReuseAddress(true);
		ssc2.socket().bind(addr);
		ssc2.register(sel, SelectionKey.OP_ACCEPT).attach("SSC2");
		
		while (true) {
			int c = sel.select();
			if (c > 0) {
				Set<SelectionKey> keys = sel.selectedKeys();
				for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext(); ) {
					SelectionKey key = it.next();
					it.remove();
					
					if (key.isValid() && key.isAcceptable()) {
						System.out.println("accept: " + key.attachment());
//						((ServerSocketChannel)key.channel()).accept().close();
//						key.interestOps(key.interestOps() & ~SelectionKey.OP_ACCEPT);
						((ServerSocketChannel)key.channel()).close();
					}
				}
			}
		}
	}

}
