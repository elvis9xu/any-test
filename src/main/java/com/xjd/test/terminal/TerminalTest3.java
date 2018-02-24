package com.xjd.test.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author elvis.xu
 * @since 2018-01-25 17:59
 */
public class TerminalTest3 {
	public static void main(String[] args) throws IOException, InterruptedException {
		Process exec = Runtime.getRuntime().exec("top");
		print(exec);
		exec.getOutputStream().write("q".getBytes());
	}


	public static void print(Process process) throws IOException, InterruptedException {
//		process.waitFor();
		StringBuilder buf = new StringBuilder(1024 * 2);

		{
			System.out.println("-----out-----\r\n");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			//			reader.close();
		}

		{
			System.out.println("-----err-----\r\n");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "utf8"));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			//			reader.close();
		}
		System.out.println(buf.toString());
	}
}
