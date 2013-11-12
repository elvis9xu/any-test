package com.xjd.test.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class MultiServerSocketListenSamePortClient {

	public static void main(String[] args) throws IOException {
		InetSocketAddress addr = new InetSocketAddress(9007);
		
		for (int i = 0; i < 10; i++) { 
			SocketChannel sc = SocketChannel.open();
			boolean f = sc.connect(addr);
	//		sc.configureBlocking(false);
			System.out.println("connect: " + f);
		}
	}

}
