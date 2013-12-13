package com.xjd.test.jsonrpc4j.client;

import java.net.MalformedURLException;
import java.net.URL;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class HttpClient {
	public static void main(String[] args) throws Throwable {
		JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:8080/any-test/api/helloService"));
		
		String resp = client.invoke("sayHello", new String[]{"XJD"}, String.class);
		System.out.println("response: " + resp);
	}
}
