package com.xjd.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author elvis.xu
 * @since 2017-08-14 17:02
 */
public class ServerSocketTest3 {
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(8080);

		for (int i = 0; i < 10; i++) {
			Socket socket = serverSocket.accept();
			System.out.println();
			System.out.printf("====accept: %1$s, %2$d====", socket.getInetAddress(), socket.getPort());
			System.out.println();
			new SocketThread(socket).start();
		}


		Thread.sleep(10000L);
		serverSocket.close();
	}

	public static class SocketThread extends Thread {
		Socket socket;

		public SocketThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				InputStream in = socket.getInputStream();

				InputStreamReader reader = new InputStreamReader(in, Charset.forName("utf8"));
				char[] cs = new char[1024];
				int i = 0;
				while ((i = reader.read(cs, 0, 1024)) != -1) {
					if (i > 0) {
						System.out.print(new String(cs, 0, i));
					}
				}

				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
