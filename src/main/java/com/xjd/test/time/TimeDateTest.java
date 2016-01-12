package com.xjd.test.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDateTest {

	public static void main(String[] args) throws ParseException {
		String s = "2011-01-01 00:00:00.000";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date d = format.parse(s);
		System.out.println(d.getTime());
	}

}
