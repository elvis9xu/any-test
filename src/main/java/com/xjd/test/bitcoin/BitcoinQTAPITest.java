package com.xjd.test.bitcoin;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.xjd.util.crypt.CoderUtil;

public class BitcoinQTAPITest {

	public static void main(String[] args) throws Throwable {
		test1();
	}

	public static void test0() throws Throwable {

		String auth = "test:test123";
		String base64 = new String(CoderUtil.encode(CoderUtil.BASE64, auth.getBytes()));
		
		String wrapline = "\n";
		Socket socket = new Socket("127.0.0.1", 18332);
		
		StringBuilder buf = new StringBuilder();
		buf.append("GET / HTTP/1.1" + wrapline);
		buf.append("Host: 127.0.0.1:18332" + wrapline);
		buf.append("Authorization: Basic " + base64 + wrapline);
		buf.append("Connection: keep-alive" + wrapline);
		buf.append("content-type: text/plain" + wrapline);
		buf.append(wrapline);
		buf.append("{\"method\":\"getbalance\",\"params\":[],\"id\":\"1\",\"jsonrpc\": \"1.0\"}");
		buf.append(wrapline);
		buf.append(wrapline);
		System.out.println("==============request================");
		System.out.println(buf.toString());

		OutputStream out = socket.getOutputStream();
//		out.write("GET / HTTP/1.1".getBytes());
//		out.write(wrapline);
//		out.write("Host: 127.0.0.1:18332".getBytes());
//		out.write(wrapline);
//		out.write(("Authorization: Basic " + base64 ).getBytes());
//		out.write(wrapline);
//		out.write("Connection: keep-alive".getBytes());
//		out.write(wrapline);
//		out.write(wrapline);
//		out.write("{\"method\":\"getgenerate\", \"id\":\"1\", \"params\":[]}".getBytes());
//		out.write(wrapline);
//		out.write(wrapline);
//		out.write(wrapline);
		out.write(buf.toString().getBytes());
		out.flush();

		System.out.println("=============response================");
		InputStream in = socket.getInputStream();
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		int i = 0;
		while ( (i = in.read()) != -1 ) {
			bout.write((byte)i);
		}

		socket.close();
		
		System.out.println(new String(bout.toByteArray()));
	}

	public static void test1() throws Throwable {
		Authenticator.setDefault(new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("test", "test123".toCharArray());
			}
			
		});
		
//		URL url = new URL("http://192.168.51.162:18332");
		URL url = new URL("http://localhost:18332");

		URLConnection urlConn = url.openConnection();
		urlConn.setDoOutput(true);
		
		urlConn.getOutputStream().write("{\"method\":\"getpeerinfo\",\"params\":[],\"id\":\"1\",\"jsonrpc\": \"2.0\"}".getBytes());
		urlConn.getOutputStream().flush();

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

	public static void test2() throws Throwable {
		JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:18332"));

		String resp = client.invoke("sayHello", new String[] { "XJD" }, String.class);
		System.out.println("response: " + resp);
	}

}
