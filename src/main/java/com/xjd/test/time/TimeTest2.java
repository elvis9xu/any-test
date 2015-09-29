package com.xjd.test.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTest2 {

	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		{
			long num = -1977039L;
			Date date = new Date(num);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = -2147483648L;
			Date date = new Date(num);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = -1798790400L;
			Date date = new Date(num);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = -71873L;
			Date date = new Date(num * 1000 * 1000);
			System.out.println(num + "---" + format.format(date));
		}
		{
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, 1967);
			Date date = c.getTime();
			System.out.println(date.getTime() / 1000 + "---" + format.format(date));
		}
		{
			long num = 1311648L;
			Date date = new Date(num * 1000 * 1000);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = 1123564L;
			Date date = new Date(num * 1000 * 1000);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = 1406553L;
			Date date = new Date(num * 1000 * 1000);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = 978278400L;
			Date date = new Date(num * 1000);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = 410198400L;
			Date date = new Date(num * 1000);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = 999999999L;
			Date date = new Date(num * 1000);
			System.out.println(num + "---" + format.format(date));
		}
		{
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, 1935);
			Date date = c.getTime();
			System.out.println(date.getTime() + "---" + format.format(date));
		}
		{
			long num = 999999999L;
			Date date = new Date(num);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = 9999999999L;
			Date date = new Date(num);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = 99999999999L;
			Date date = new Date(num);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = 999999999999L;
			Date date = new Date(num);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = 9999999999L;
			Date date = new Date(num * 1000);
			System.out.println(num + "---" + format.format(date));
		}
		{
			long num = 1000000000L;
			Date date = new Date(num * 1000);
			System.out.println(num + "---" + format.format(date));
		}
	}

}
