package com.xjd.test.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {

	public static void main(String[] args) {
//		long t = 4294967295L;
//
//		System.out.println(System.currentTimeMillis());
//		System.out.println(t * 1000);
//		System.out.println(new Date(t * 1000L));
		
		
		Date now = new Date();
		System.out.println(now.getTime());
		
		Date d = new Date(now.getTime());
		String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(d);
		System.out.println(s);
	}

}
