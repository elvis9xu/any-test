package com.xjd.test.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author elvis.xu
 * @since 2017-10-17 10:07
 */
public class DateTest4 {
	public static void main(String[] args) {
		Date date = new Date(673023600000L);
//		Date date = new Date(1508212039765L);
		System.out.println(date.getTime());
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		c.set(2017, 4, 1, 0, 0, 0);
		System.out.println(c.getTimeInMillis());
		Date date2 = new Date(c.getTimeInMillis());

		System.out.println(date);

		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			System.out.println(simpleDateFormat.format(date));
			System.out.println(simpleDateFormat.format(date2));
		}
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			System.out.println(simpleDateFormat.format(date));
			System.out.println(simpleDateFormat.format(date2));
		}
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
			System.out.println(simpleDateFormat.format(date));
			System.out.println(simpleDateFormat.format(date2));
		}
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			System.out.println(simpleDateFormat.format(date));
			System.out.println(simpleDateFormat.format(date2));
		}
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+9"));
			System.out.println(simpleDateFormat.format(date));
			System.out.println(simpleDateFormat.format(date2));
		}

	}
}
