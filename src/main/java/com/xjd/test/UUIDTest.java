package com.xjd.test;

import java.util.UUID;

public class UUIDTest {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println(UUID.randomUUID());
		String s = UUID.randomUUID().toString();
		System.out.println(UUID.fromString(s));
		System.out.println(UUID.fromString(s));

	}

}
