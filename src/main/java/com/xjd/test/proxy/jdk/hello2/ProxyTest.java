package com.xjd.test.proxy.jdk.hello2;

/**
 * @author elvis.xu
 * @since 2018-08-11 12:22
 */
public class ProxyTest {
	public static void main(String[] args) {
		B b = new B();
		BHandler bHandler = new BHandler(b);

		Object proxy = bHandler.getProxy();

		System.out.println(((IB) proxy).getBean().getClass());

		System.out.println(((B) proxy).getBean().getClass());
	}
}
