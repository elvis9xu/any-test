package com.xjd.test.number;

public class FloatEqual {

	public static void main(String[] args) {
		Float f1 = Float.valueOf(2.345f);
		Float f2 = Float.valueOf("2.345");
		
		System.out.println(f1.equals(f2));
	}

}
