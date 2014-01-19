package com.xjd.test.bitcoin.jsonrpc;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class HttpClientTest {
    public static void main(String[] args) throws Throwable {
	Authenticator.setDefault(new Authenticator() {

	    @Override
	    protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("test", "test123".toCharArray());
	    }

	});

	JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://192.168.1.103:18332/"));

	{
	    Object resp = client.invoke("getbalance", null, Object.class);
	    System.out.println(resp);
	}
	
	{
	    Object resp = client.invoke("listaccounts", null, Object.class);
	    System.out.println(resp.getClass());
	    System.out.println(resp);
	}

    }
}
