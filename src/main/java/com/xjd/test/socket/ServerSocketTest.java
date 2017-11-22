package com.xjd.test.socket;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.util.StreamUtils;

public class ServerSocketTest {

	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(8080);

		Socket socket = ss.accept();

		InputStream in = socket.getInputStream();
		OutputStream os = socket.getOutputStream();

		InputStream fin = SocketTest.class.getResourceAsStream("response1.txt");
		StreamUtils.copy(fin, os);
		os.flush();

		FileOutputStream out = new FileOutputStream("request3.txt");
		byte[] bs = new byte[1024 * 2];
		int c = 0;
		while ((c = in.read(bs)) != -1) {
			if (c > 0) {
				System.out.println(new String(bs, 0, c));
				out.write(bs, 0, c);
				out.flush();
			}
		}

		fin.close();
		in.close();
		socket.close();
		ss.close();
		os.close();
		out.close();
		Thread.sleep(3000L);
	}

}
