package com.xjd.test.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author elvis.xu
 * @since 2017-10-17 10:07
 */
public class DateTest2 {
	public static void main(String[] args) {
//		Date date = new Date(673023600000L);
//		Date date = new Date(673023600051L);
		Date date = new Date(673027200709L);

		System.out.println(date);

		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			System.out.println(simpleDateFormat.format(date));
		}
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			System.out.println(simpleDateFormat.format(date));
		}
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
			System.out.println(simpleDateFormat.format(date));
		}
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			System.out.println(simpleDateFormat.format(date));
		}

		for (String s : TimeZone.getAvailableIDs()) {
			System.out.println(s);
		}

		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		c.set(1991, 4, 1, 0, 0, 0);
		System.out.println(c.getTimeInMillis());

		for (Locale locale : Calendar.getAvailableLocales()) {
			System.out.println(locale);
		}
//
//		Calendar c = Calendar.getInstance();
//		c.setTime(date);
//
//		System.out.println(c);

//		System.out.println(TimeZone.getDefault());
	}
}
