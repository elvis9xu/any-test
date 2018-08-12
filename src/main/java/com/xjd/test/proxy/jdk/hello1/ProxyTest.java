package com.xjd.test.proxy.jdk.hello1;

/**
 * @author elvis.xu
 * @since 2018-08-11 11:30
 */
public class ProxyTest {



	public static void main(String[] args) {
		A a = new A();
		AHandler aHandler = new AHandler(a);

		Object proxy = aHandler.getProxy();

		IA ia = (IA) proxy;
		System.out.println(ia.sayHello("XXX"));

		IA2 ia2 = (IA2) proxy;
		System.out.println(ia2.sayHi("yyy"));
	}
}
