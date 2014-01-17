package com.xjd.test.grammar;

/**
 * <pre>
 * TODO
 * </pre>
 * @author elvis.xu
 * @since Jan 7, 2014 2:59:33 PM
 */
public class B extends A {
	public static String m1 = "B-Static";
	public String m2 = "B-Instance";

	@Override
	public void saySome() {
		super.saySome();
		System.out.println("B: " + m1 + " / " + m2);
		System.out.println("B: " + getM1());
	}

	@Override
	public String getM1() {
		return m1;
	}

	public static void main(String[] args) {
		new B().saySome();
	}
}
