package com.xjd.test.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author elvis.xu
 * @since 2018-01-25 17:34
 */
public class TerminalTest {
	public static void main(String[] args) throws IOException {
		System.out.println(run("ls -l /"));
		System.out.println(run("whoami"));
		System.out.println(run("cat /etc/passwd"));
		System.out.println(run("pwd"));
		System.out.println(run("cat /etc/ssh/sshd_config"));
	}

	public static String run(String cmd) throws IOException {
		Process process = Runtime.getRuntime().exec(cmd);


		StringBuilder buf = new StringBuilder(1024 * 2);

		{
			buf.append("-----out-----\r\n");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				buf.append(line).append("\r\n");
			}
			reader.close();
		}

		{
			buf.append("-----err-----\r\n");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "utf8"));
			String line;
			while ((line = reader.readLine()) != null) {
				buf.append(line).append("\r\n");
			}
		}
		return buf.toString();
	}


}
