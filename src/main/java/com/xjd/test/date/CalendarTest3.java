package com.xjd.test.date;

import java.util.Calendar;

import org.apache.commons.lang3.time.DateUtils;

public class CalendarTest3 {

	public static void main(String[] args) {
		Calendar c = DateUtils.truncate(Calendar.getInstance(), Calendar.DAY_OF_MONTH);
		System.out.println(c.getTime());
	}

}
