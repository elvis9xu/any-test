package com.xjd.test.jsonrpc4j.service;

public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "Hello " + name + ", welcome to use jsonrpc4j!";
	}

}
