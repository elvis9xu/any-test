package com.xjd.test.codec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;

public class Base64Test {

	public static void main(String[] args) throws IOException {
		String s = "W3siZXhwcmVzc0NvbUlkIjoieXVhbnRvbmciLCJleHByZXNzQ29tTmFtZSI6IuWchumAmuW/q+mAkiIsImV4cHJlc3NJZCI6IjgwNTEzMjQxNTI0NCIsIm9yZGVyTnVtYmVyIjoiUDIwMTYwMTA4MTg0NTI3MDAwMjkifSx7ImV4cHJlc3NDb21JZCI6Inl1YW50b25nIiwiZXhwcmVzc0NvbU5hbWUiOiLlnIbpgJrlv6vpgJIiLCJleHByZXNzSWQiOiI4MDUyMjA4OTkxMzYiLCJvcmRlck51bWJlciI6IlAyMDE2MDEwNzE1MjIwOTAwMDI2In1d";
		System.out.println("传递: " + s);
		byte[] sb = Base64.decodeBase64(s.getBytes("utf8"));
		String sd = new String(sb, "utf8");
		System.out.println("解传: " + sd);
//		String ts = URLEncoder.encode(s, "utf8");
//		System.out.println(ts);
//		System.out.println(s);
//		s = URLDecoder.decode(s, "utf8");
//		System.out.println(s);
//		byte[] bs = Base64.decodeBase64(s);
//		System.out.println(new String(bs, "utf8"));
		
		InputStream in = Base64Test.class.getResourceAsStream("txt.txt");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int c = 0;
		while ((c = in.read(buf)) != -1) {
			out.write(buf, 0, c);
		}
		in.close();
		out.close();
		
		String oo = new String(out.toByteArray(), "utf8");
		System.out.println("原串: " + oo);
		String en = Base64.encodeBase64String(oo.getBytes("utf8"));
		System.out.println("编码: " + en);
		String ue = URLEncoder.encode(en, "utf8");
		System.out.println("U编: " + ue);
		byte[] bs = Base64.decodeBase64(en.getBytes("utf8"));
		String de = new String(bs, "utf8");
		System.out.println("解码: " + de);
		
		String t = URLEncoder.encode("a+b", "utf8");
		System.out.println(t);
	}

}
