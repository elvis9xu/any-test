package com.xjd.test.trycatch;

/**
 * <pre>
 * TODO
 * </pre>
 * @author elvis.xu
 * @since Jan 17, 2014 1:22:44 PM
 */
public class TryCatchTest {
	public static void main(String[] args) {
		System.out.println(test());
		System.out.println(test2());
	}

	@SuppressWarnings("finally")
	public static String test() {
		try {
			return "HELLO";
		} finally {
			return "WORLD";
		}
	}

	@SuppressWarnings("finally")
	public static String test2() {
		try {
			throw new Exception("HELLO");
		} catch (Exception e) {
			return "HELLO";
		} finally {
			return "WORLD";
		}
	}
}
