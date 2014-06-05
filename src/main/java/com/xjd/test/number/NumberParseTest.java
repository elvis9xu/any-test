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
//		testLong();
		
		String s = "692233210014286710";
		int l = (int) Long.parseLong(s);
		System.out.println(l);
	}
	
	public static void test() {
		String s = "000001";
		int i = Integer.parseInt(s);
		System.out.println(i);
		
		s = "000001.02000";
		double d = Double.parseDouble(s);
		System.out.println(d);
	}
	
	public static void test2() {
		Integer i = tr();
	}
	
	public static <T extends Number> T tr() {
		return null;
	}
	
	public static void testLong() {
		Long l = 1000L;
		System.out.println(l + "");
		
		l = Long.valueOf("1000");
		System.out.println(l + "");
		
		l = new Long(1000L);
		System.out.println(l + "");
	}

}
