package com.xjd.test.date.java8;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author elvis.xu
 * @since 2017-10-19 18:06
 */
public class Hello {
	public static void main(String[] args) {
		Date date = new Date();
		Instant instant = Instant.now();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant, ZoneId.ofOffset("GMT", ZoneOffset.of("+8")));
		LocalDateTime localDateTime3 = LocalDateTime.ofInstant(instant, ZoneId.ofOffset("GMT", ZoneOffset.of("+9")));


		System.out.println(date.getTime());
		System.out.println(instant.toEpochMilli());

		System.out.println(date);
		System.out.println(localDateTime);
		System.out.println(localDateTime2);
		System.out.println(localDateTime3);

	}
}
