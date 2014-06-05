package com.xjd.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarTest {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance(Locale.CHINA);

		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 31);

		System.out.println(format.format(c.getTime()));
		System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));

		c.add(Calendar.MONTH, 1);
		System.out.println(format.format(c.getTime()));
		System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		c.add(Calendar.MONTH, 1);
		System.out.println(format.format(c.getTime()));
		System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));

//		String s = "2014-02-31";
//		Date date = format.parse(s);
//		System.out.println(format.format(date));

	}

}
