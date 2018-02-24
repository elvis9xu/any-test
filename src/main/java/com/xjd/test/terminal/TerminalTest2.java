package com.xjd.test.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author elvis.xu
 * @since 2018-01-25 17:49
 */
public class TerminalTest2 {
	public static void main(String[] args) throws IOException, InterruptedException {
		Process exec = Runtime.getRuntime().exec("echo '请输入命令'");
		print(exec);

		while (true) {
			input(exec, read());
			print(exec);
		}
	}

	public static String read() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "utf8"));
		String line = reader.readLine();
//		reader.close();
		return line;
	}

	public static void input(Process process, String cmd) throws IOException {
		process.getOutputStream().write(cmd.getBytes("utf8"));
	}

	public static void print(Process process) throws IOException, InterruptedException {
		process.waitFor();
		StringBuilder buf = new StringBuilder(1024 * 2);

		{
			buf.append("-----out-----\r\n");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				buf.append(line).append("\r\n");
			}
//			reader.close();
		}

		{
			buf.append("-----err-----\r\n");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "utf8"));
			String line;
			while ((line = reader.readLine()) != null) {
				buf.append(line).append("\r\n");
			}
//			reader.close();
		}
		System.out.println(buf.toString());
	}
}
