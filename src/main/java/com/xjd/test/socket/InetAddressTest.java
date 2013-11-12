package com.xjd.test.socket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		System.out.println(InetAddress.getLocalHost()); //InetAddress.getLocalHost()
		System.out.println(new InetSocketAddress(9121).getAddress()); //InetAddress.anyLocalAddress()
	}

}

