package com.xjd.test.string;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class StringTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "中文会乱码！哈哈【了】";
		
		System.out.println(new String(s.getBytes()));
		System.out.println(new String(s.getBytes(), "utf8"));
		System.out.println(new String(s.getBytes(), "gbk"));
		System.out.println(new String(s.getBytes(), "gbk"));
		System.out.println(new String(s.getBytes("GBK"), "gbk"));
		System.out.println(new String(s.getBytes("GBK"), "utf8"));
		System.out.println(new String(s.getBytes("utf8"), "gbk"));
		System.out.println(Charset.defaultCharset());
	}

}
