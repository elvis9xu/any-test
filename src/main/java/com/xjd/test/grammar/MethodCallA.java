package com.xjd.test.grammar;

public class MethodCallA {

	public void a() {
		System.out.println("A.a()");
	}
	
	public void b() {
		System.out.println("A.b()");
		a();
	}
}
