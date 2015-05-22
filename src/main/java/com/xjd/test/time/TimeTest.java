package com.xjd.test.time;

import java.util.Date;

public class TimeTest {

	public static void main(String[] args) {
//		long t = 4294967295L;
//
//		System.out.println(System.currentTimeMillis());
//		System.out.println(t * 1000);
//		System.out.println(new Date(t * 1000L));

//		System.out.println(new Date().getTime());
//
//		long t = new Date().getTime();
//		long utcTIme = Calendar.getInstance(TimeZone.getTimeZone("+0:00")).getTime().getTime();
//		System.out.println(t);
//		System.out.println(utcTIme);
//		System.out.println(new Date());

		long t = new Date().getTime();

		long d = t / 1000 / 60 / 60 / 24;
		System.out.println(d);

		long r = t - d * 24 * 60 * 60 * 1000;
		System.out.println(r);

		long h = r / 1000 / 60 / 60;
		System.out.println(h);

		r = r - h * 60 * 60 * 1000;
		System.out.println(r);

		long m = r / 1000 / 60;
		System.out.println(m);
	}

}
