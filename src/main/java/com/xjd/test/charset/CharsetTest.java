package com.xjd.test.charset;

public class CharsetTest {

	public static void main(String[] args) {
		// String s = "\u300a";
		// String t = new String(s.getBytes(Charset.forName("ASCII")), Charset.forName("UTF8"));
		// System.out.println(t);

		char c = '\u300a';
		System.out.println(c);

		String s = "\u300a";
		System.out.println(s.charAt(0));

		String s2 = "300a";
		Integer i = Integer.parseInt(s2, 16);
		System.out.println(i);
		System.out.println((char) (int) i);
	}

}
