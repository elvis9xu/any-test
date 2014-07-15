package com.xjd.test.time;

import java.util.Date;

public class TimeTest {

	public static void main(String[] args) {
		long t = 4294967295L;

		System.out.println(System.currentTimeMillis());
		System.out.println(t * 1000);
		System.out.println(new Date(t * 1000L));
	}

}
