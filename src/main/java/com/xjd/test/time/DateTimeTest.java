package com.xjd.test.time;

import hirondelle.date4j.DateTime;

import java.util.TimeZone;

public class DateTimeTest {

	public static void main(String[] args) {
		// DateTime datetime = DateTime.now(TimeZone.getTimeZone("GMT"));
		// DateTime datetime2 = DateTime.now(TimeZone.getTimeZone("GMT+8:00"));
		// System.out.println(datetime.getHour());
		// System.out.println(datetime2.getHour());
		// System.out.println(datetime.getMilliseconds(TimeZone.getTimeZone("GMT")));
		// System.out.println(datetime.getMilliseconds(TimeZone.getTimeZone("GMT+8:00")));
		// System.out.println(datetime2.getMilliseconds(TimeZone.getTimeZone("GMT")));
		// System.out.println(datetime2.getMilliseconds(TimeZone.getTimeZone("GMT+8:00")));
		//
		// long n = datetime.changeTimeZone(TimeZone.getTimeZone("GMT"),
		// TimeZone.getTimeZone("GMT+8:00")).getMilliseconds(
		// TimeZone.getTimeZone("GMT"));
		// System.out.println(n);

		// long cm = System.currentTimeMillis();
		// Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		// calendar.setTimeInMillis(cm);
		//
		// System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		//
		// calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00"));
		// calendar.setTimeInMillis(cm);
		// System.out.println(calendar.get(Calendar.HOUR_OF_DAY));

		DateTime dateTime = DateTime.now(TimeZone.getDefault());
		System.out.println(dateTime.getWeekDay());
		System.out.println(dateTime.getWeekIndex());
	}

}
