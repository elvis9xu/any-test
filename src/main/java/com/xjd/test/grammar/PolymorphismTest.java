package com.xjd.test.grammar;

public class PolymorphismTest {

	public static String hello(String... msgs) {
		return "MSGS";
	}
	
	public static String hello(String h, String m) {
		return "2MSG";
	}
	
	/**
	 * <pre>
	 * 精确匹配优先
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(hello("M","D"));
		System.out.println(hello("M",null));
	}
}
