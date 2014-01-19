package com.xjd.test.bitcoin.jsonrpc;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringClientTest {

    public static void main(String[] args) {
	Authenticator.setDefault(new Authenticator() {

	    @Override
	    protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("test", "test123".toCharArray());
	    }

	});
	
	ApplicationContext contxt = new ClassPathXmlApplicationContext("spring-client.xml", SpringClientTest.class);
	
	BitcoinService btc = contxt.getBean(BitcoinService.class);
	
	System.out.println(btc.getbalance());
	
	System.out.println(btc.listaccounts());
	
//	System.out.println(btc.getnewaddress("GOOD"));
	
	System.out.println(btc.getblock("00000000b873e79784647a6c82962c70d228557d24a747ea4d1b8bbe878e1206"));
    }
    

}
