package com.xjd.test.http;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author elvis.xu
 * @since 2017-10-12 18:34
 */
public class HttpTest2 {
	public static void main(String[] args) throws UnknownHostException {
		System.out.println(InetAddress.getLocalHost().getHostAddress());
	}
}
