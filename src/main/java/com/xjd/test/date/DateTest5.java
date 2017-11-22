package com.xjd.test.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author elvis.xu
 * @since 2017-10-17 10:07
 */
public class DateTest5 {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
//		c.set(1991, 4, 1, 0, 0, 0);
//		c.set(1991, 3, 13, 0, 0, 0);
		System.out.println(c.getTimeInMillis());
		Date date = new Date(c.getTimeInMillis());

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
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+9"));
			System.out.println(simpleDateFormat.format(date));
		}

	}
}
