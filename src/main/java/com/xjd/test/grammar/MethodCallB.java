package com.xjd.test.grammar;

/**
 * <pre>
 * B.b --> A.b --> B.a
 * X A.a
 * </pre>
 * @author elvis.xu
 * @since 2014-2-11
 */
public class MethodCallB extends MethodCallA {

	@Override
	public void a() {
		System.out.println("B.a()");
	}

	@Override
	public void b() {
		System.out.println("B.b()");
		super.b();
	}

	public static void main(String[] args) {
		new MethodCallB().b();
	}
}
