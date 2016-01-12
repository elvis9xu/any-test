package com.xjd.test.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BigDecimalTest {

	public static void main(String[] args) {
		BigDecimal ta = new BigDecimal(1L);
		ta = ta.divide(new BigDecimal(100));
		ta.setScale(2, RoundingMode.HALF_UP);
		String amtStr = ta.toString() + "元";
		System.out.println(ta.toString());
		System.out.println(ta.toPlainString());

		DecimalFormat format = new DecimalFormat();
		format.setMinimumFractionDigits(2);
		format.setGroupingUsed(false);
		System.out.println(format.format(ta));
		
		System.out.println("===========");
		BigDecimal yuan = BigDecimal.valueOf(2L);
		yuan = yuan.divide(BigDecimal.valueOf(100L));
		yuan.setScale(2, RoundingMode.HALF_UP);
		System.out.println(yuan);
	}

}
