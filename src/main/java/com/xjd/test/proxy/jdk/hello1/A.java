package com.xjd.test.proxy.jdk.hello1;

/**
 * @author elvis.xu
 * @since 2018-08-11 11:29
 */
public class A implements IA, IA2 {
	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

	@Override
	public String sayHi(String name) {
		return "Hi " + name;
	}
}
