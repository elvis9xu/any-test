package com.xjd.test.httpclient;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class FileSubmitTest {

	public static void main(String[] args) {
		HttpClient httpClient = HttpClients.createDefault();

//		HttpPost httpPost = new HttpPost("http://10.83:8095/api/10/upload");
		HttpPost httpPost = new HttpPost("http://localhost:8095");

		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000)
				.setSocketTimeout(30000).build();
		httpPost.setConfig(requestConfig);

		MultipartEntityBuilder mb = MultipartEntityBuilder.create();
		mb.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//		mb.addBinaryBody("file", new File("D:/tmp/1.sql"));
		mb.addBinaryBody("file", new File("D:/tmp/source.jpg"));
		mb.addTextBody("chr", "platform");
		mb.addTextBody("token", "DCC6EAC7B0F94F5DAE64320A19E380DE");
		mb.addTextBody("timestamp", System.currentTimeMillis() + "");
		mb.addTextBody("forBiz", "1");

		httpPost.setEntity(mb.build());

		try {
			HttpResponse response = httpClient.execute(httpPost);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String res = EntityUtils.toString(response.getEntity());
				System.out.println(res);
			} else {
				// TODO
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
