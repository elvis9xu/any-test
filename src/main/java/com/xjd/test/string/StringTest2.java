package com.xjd.test.string;

import java.io.UnsupportedEncodingException;

public class StringTest2 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "abcde";

		System.out.println(s.substring(s.lastIndexOf('/') + 1));
	}

}
