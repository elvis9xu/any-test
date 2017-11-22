package com.xjd.test.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author elvis.xu
 * @since 2017-08-18 20:29
 */
public class ForEachTest {
	public static void main(String[] args) {
		List list = new ArrayList(Arrays.asList(1, 2, 3));

		list.forEach(e -> {
			list.remove(e);
		});
	}
}
