package com.xjd.test.proxy.jdk.hello2;

/**
 * @author elvis.xu
 * @since 2018-08-11 12:20
 */
public class B implements IB {
	@Override
	public BBean2 getBean() {
		return new BBean2();
	}

	public static class BBean2 extends BBean {
		String name2 = "name2";
	}
}
