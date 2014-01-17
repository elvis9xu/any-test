package com.xjd.test.grammar;

/**
 * <pre>
 * TODO
 * </pre>
 * @author elvis.xu
 * @since Jan 7, 2014 2:56:52 PM
 */
public class A {
	public static String m1 = "A-Static";
	public String m2 = "A-Instance";
	
	public void saySome() {
		System.out.println("A: " + m1 + " / " + m2);
		System.out.println("A: " + getM1());
	}
	
	public String getM1() {
		return m1;
	}
	
	public static void main(String[] args) {
		new A().saySome();
	}
}
