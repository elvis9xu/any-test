package com.xjd.test.random;

import java.util.Random;

/**
 * @author elvis.xu
 * @since 2018-04-24 10:39
 */
public class RandomTest {
	public static void main(String[] args) {
		// 种子一样，每次产生的随机数序列一样
		Random random = new Random(1L);
		for (int i = 0; i < 5; i++) {
			System.out.println(random.nextInt());
		}
		System.out.println("================");
		random = new Random(1L);
		for (int i = 0; i < 5; i++) {
			System.out.println(random.nextInt());
		}
	}
}
