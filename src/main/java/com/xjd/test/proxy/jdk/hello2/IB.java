package com.xjd.test.proxy.jdk.hello2;

/**
 * @author elvis.xu
 * @since 2018-08-11 12:17
 */
public interface IB {

	BBean getBean();

	public static class BBean {
		String name = "BBean name";
	}
}
