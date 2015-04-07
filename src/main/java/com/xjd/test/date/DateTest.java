package com.xjd.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DateTest {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String s = format.format(now);
		Date t = format.parse(s);
		System.out.println(format2.format(t));
		System.out.println(format2.format(DateUtils.truncate(now, Calendar.DAY_OF_MONTH)));
	}

}
