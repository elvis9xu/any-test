package com.xjd.test.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author elvis.xu
 * @since 2017-09-01 18:45
 */
public class IteratorTest {
	public static void main(String[] args) {
		LinkedList list = new LinkedList(Arrays.asList(1,2,3,4,5));
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {

			Object next = iterator.next();
			if ( 3 == (Integer) next) {
				iterator.remove();
			}
		}
		System.out.println(Arrays.toString(list.toArray()));
	}
}
