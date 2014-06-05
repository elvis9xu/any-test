package com.xjd.test.number;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberTransformTest {

	public static void main(String[] args) {
		BigDecimal bd = new BigDecimal(100011L);
		
		
		BigDecimal bd2 = bd.divide(new BigDecimal(1000.0000));
		
		BigDecimal bd3 = bd2.setScale(2, RoundingMode.HALF_EVEN);
		System.out.println(bd3.toString());
		
		
		String s = new BigDecimal(10000111L).divide(new BigDecimal(1000)).setScale(2, RoundingMode.HALF_EVEN).toString();
		System.out.println(s);
		
	}

}
