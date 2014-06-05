package com.xjd.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParseTest {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @author elvis.xu
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String s = "23:59:59";
		Date date = format.parse(s);
		System.out.println(format2.format(date));
		
		SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
		Date d = format3.parse(format3.format(new Date()));
		System.out.println(d.getHours());
		System.out.println(d.getMinutes());
	}

}
