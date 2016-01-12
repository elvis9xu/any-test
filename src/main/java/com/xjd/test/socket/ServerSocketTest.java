package com.xjd.test.socket;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(80);

		Socket socket = ss.accept();

		InputStream in = socket.getInputStream();
		FileOutputStream out = new FileOutputStream("D:/request3.txt");
		byte[] bs = new byte[1024 * 2];
		int c = 0;
		while ((c = in.read(bs)) != -1) {
			if (c > 0) {
				System.out.println(new String(bs, 0, c));
				out.write(bs, 0, c);
				out.flush();
			}
		}
		
		in.close();
		socket.close();
		ss.close();
		out.close();
	}

}
