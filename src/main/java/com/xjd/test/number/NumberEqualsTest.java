package com.xjd.test.number;

import java.util.ArrayList;
import java.util.List;

public class NumberEqualsTest {

	public static void main(String[] args) {
		Long l1 = Long.valueOf(10L);
		Long l2 = Long.valueOf("10");

		System.out.printf("%d, %d", l1.hashCode(), l2.hashCode());
		System.out.println();

		List<Long> list = new ArrayList<Long>();
		list.add(l1);
		System.out.println(list.contains(l2));
	}

}
