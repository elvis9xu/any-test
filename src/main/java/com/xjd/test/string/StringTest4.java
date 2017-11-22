package com.xjd.test.string;

/**
 * @author elvis.xu
 * @since 2017-10-12 17:25
 */
public class StringTest4 {
	public static void main(String[] args) {
		String s = "/a/b/c/d/e/f";
		String s1 = "/a/b/c/d";

		System.out.println(s.lastIndexOf('/'));
		System.out.println(s.indexOf('/', s1.length()));
	}
}
