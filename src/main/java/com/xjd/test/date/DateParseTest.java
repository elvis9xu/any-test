package com.xjd.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParseTest {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @author elvis.xu
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String s1 = "20130102 13:13:13";
		String s2 = "                 ";
		String s3 = "";
		Date d1 = format.parse(s1);
		Date d2 = format.parse(s2);
		Date d3 = format.parse(s3);
	}

}
