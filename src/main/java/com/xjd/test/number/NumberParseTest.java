package com.xjd.test.number;

public class NumberParseTest {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @author elvis.xu
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "000001";
		int i = Integer.parseInt(s);
		System.out.println(i);
		
		s = "000001.02000";
		double d = Double.parseDouble(s);
		System.out.println(d);
	}

}
