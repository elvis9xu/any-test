package com.xjd.test.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author elvis.xu
 * @since 2015-05-03 23:35
 */
public class ClientSocketTest {
	public static void main(String[] args) throws IOException {
		// http://tuku.hxd57.com/uploads/allimg/150203/1-150203150T8.jpg
		// http://img0.bdstatic.com/img/image/2043d07892fc42f2350bebb36c4b72ce1409212020.jpg
		Socket socket = new Socket("img0.bdstatic.com", 80);

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
		writer.write("GET /img/image/2043d07892fc42f2350bebb36c4b72ce1409212020.jpg HTTP/1.1");
		writer.newLine();
		writer.write("Host: img0.bdstatic.com");
		writer.newLine();
		writer.write("Connection: keep-alive");
		writer.newLine();
		writer.write("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		writer.newLine();
		writer.write("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36");
		writer.newLine();
		writer.write("Accept-Encoding: gzip, deflate, sdch");
		writer.newLine();
		writer.newLine();
		writer.flush();

		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		socket.close();
	}
}
