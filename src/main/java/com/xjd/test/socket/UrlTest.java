package com.xjd.test.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UrlTest {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://139.129.13.123/pic/1c1108b41eb6fc9255cefba81345233c_225x224.jpg");
		InputStream in = url.openConnection().getInputStream();
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] buf = new byte[1024 * 2];
		int c = 0;
		int t = 0;
		while ((c = in.read(buf)) != -1) {
			t += c;
			if (c > 0) {
				bout.write(buf, 0, c);
				System.out.println(new String(buf, 0, c, "UTF-8"));
			} else {
				System.out.println("=====totall: " + t + "===========");
			}
		}
		bout.flush();
		in.close();
		System.out.println("=========totall: " + t + "=============");
	}
}
