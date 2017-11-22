package com.xjd.test.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class SocketTest {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket();
//		socket.connect(new InetSocketAddress("api3.91jkys.com", 8095));
		socket.connect(new InetSocketAddress("localhost", 8971));
		socket.setKeepAlive(false);
		
		OutputStream out = socket.getOutputStream();
		InputStream in = SocketTest.class.getResourceAsStream("request4.txt");
		byte[] buf = new byte[1024 * 2];
		int c = 0;
		while ((c = in.read(buf)) != -1) {
			if (c > 0) {
				out.write(buf, 0, c);
			}
		}
//		out.write('\r');
//		out.write('\n');
		out.flush();
		in.close();
		
		in = socket.getInputStream();
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		int t = 0;
		while ((c = in.read(buf)) != -1) {
			t += c;
			if (c > 0) {
				bout.write(buf, 0, c);
				System.out.println(new String(buf, 0, c, "UTF-8"));
			} else {
//				System.out.println("=====totall: " + t + "===========");
			}
		}
		bout.flush();
		socket.close();
		bout.close();
		buf = bout.toByteArray();
		String txt = new String(buf, "UTF-8");
		System.out.println(txt);
		System.out.println("=====totall: " + t + "===========");
	}
}
