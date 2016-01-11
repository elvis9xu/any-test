package com.xjd.test.urlencode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author elvis.xu
 * @since 2015-12-20 14:55
 */
public class URLEncoderTest {
	public static void main(String[] args) throws IOException {
//		String s = "?中国=中文&hello=Hello World!&ch=中";
//
//		String es = URLEncoder.encode(s, "UTF-8");
//
//		System.out.println(es);

		URL url = new URL("http://139.129.13.123/api/10/signin");

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("POST");
		InputStream in = urlConnection.getInputStream();
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024 * 2];
			int c = -1;
			while ((c = in.read(buf)) != -1) {
				if (c > 0) {
					out.write(buf, 0, c);
				}
			}
			String res = new String(out.toByteArray(), "utf8");
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
