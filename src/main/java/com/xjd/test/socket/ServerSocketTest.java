package com.xjd.test.socket;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(8080);

		Socket socket = ss.accept();

		InputStream in = socket.getInputStream();

		byte[] bs = new byte[1024 * 2];
		int c = 0;
		while ((c = in.read(bs)) != -1) {
			if (c > 0) {
				System.out.println(new String(bs, 0, c));
			}
		}
		
		in.close();
		socket.close();
		ss.close();
	}

}
