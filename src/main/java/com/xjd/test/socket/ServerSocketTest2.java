package com.xjd.test.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author elvis.xu
 * @since 2017-08-14 17:02
 */
public class ServerSocketTest2 {
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(8080);

		for (int i = 0; i < 10; i++) {
			Socket socket = serverSocket.accept();

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

				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String line = null;

				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}


				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
