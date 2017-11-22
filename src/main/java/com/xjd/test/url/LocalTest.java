package com.xjd.test.url;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author elvis.xu
 * @since 2017-09-04 18:07
 */
public class LocalTest {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println(localHost.getHostName());
		System.out.println(localHost.getCanonicalHostName());
		System.out.println(localHost.getHostAddress());
		System.out.println(localHost.getAddress());
	}
}
