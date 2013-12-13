package com.xjd.test.blockchaininfo;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class BlockChainAPITest {

	public static void main(String[] args) throws Throwable {
		URL url = new URL("https://blockchain.info/zh-cn/merchant/8e32db72-a1cc-4909-ae2e-72ee090bb73a/balance?password=elvis9xuqq1015012955");
//		URL url = new URL("https://blockchain.info/zh-cn/merchant/8e32db72-a1cc-4909-ae2e-72ee090bb73a/list?password=elvis9xuqq1015012955");
//		URL url = new URL("https://blockchain.info/zh-cn/merchant/8e32db72-a1cc-4909-ae2e-72ee090bb73a/archive_address?password=elvis9xuqq1015012955&address=1Ht1Uh28M1br7kFn7XfkxrDsKQvAtBS2DB");
//		URL url = new URL("https://blockchain.info/zh-cn/merchant/8e32db72-a1cc-4909-ae2e-72ee090bb73a/new_address?password=elvis9xuqq1015012955&label=\"FOR%20TEST2\"");
		
		URLConnection urlConn = url.openConnection();
		
		InputStream in;
		if (urlConn instanceof HttpsURLConnection) {
			HttpsURLConnection hUrlCon = (HttpsURLConnection) urlConn;
			in = hUrlCon.getInputStream();
		} else {
			in = urlConn.getInputStream();
		}
		
		InputStreamReader read = new InputStreamReader(in, "UTF-8");
		
		char[] cs = new char[1024];
		int i = 0;
		while ((i = read.read(cs)) != -1) {
			System.out.print(new String(cs, 0, i));
		}
		
		read.close();
	}
	
	public static void walletAPITest() {
		
	}
	
	public static void getBalance() {
		
	}

}
