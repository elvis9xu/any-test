package com.xjd.test.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ZoneTest {

	public static void main(String[] args) throws ParseException {
//		for (String id: TimeZone.getAvailableIDs()) {
//			System.out.println(id);
//		}
		System.out.println("=====================");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
		format2.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		Date d = new Date();

		System.out.println(format.format(d));
		System.out.println(format2.format(d));

		long l = d.getTime();
		System.out.println(l);
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		c.setTimeInMillis(l);
		System.out.println(format.format(c.getTime()));
		System.out.println(format2.format(c.getTime()));
		
		System.out.println("=============");
		String s = format2.format(c.getTime());
		System.out.println(s);
		Date d1 = format.parse(s);
		Date d2 = format2.parse(s);
		System.out.println(format.format(d1));
		System.out.println(format.format(d2));
		System.out.println(format2.format(d1));
		System.out.println(format2.format(d2));
	}

}
