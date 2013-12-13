package com.xjd.test.jsonrpc4j.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringClient {
	
	public static void main(String[] args) {
		ApplicationContext contxt = new ClassPathXmlApplicationContext("spring-client.xml", SpringClient.class);
		
		HelloClient client = contxt.getBean(HelloClient.class);
		System.out.println(client.sayHello("XJD"));
	}

	public static interface HelloClient {
		String sayHello(String s);
	}
}
