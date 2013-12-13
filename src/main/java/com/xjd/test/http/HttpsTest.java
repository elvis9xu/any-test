package com.xjd.test.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;


/**
 * <pre>
 * 要想在java中使用HTTPS, 即SSL/TLS, 需要为JVM环境导入证书, 步骤如下:
 * 1. 从服务器下载证书
 * 2. 或从IE中下载(查看证书, 导出证书)
 * 3. 使用命令keytool -import -alias tomcat -keystore "%JAVA_HOME%/jre/lib/security/cacer -file d:/test/jks.cer
 * 	  密码默认为changeit
 * 4. 在程序中可以使用HttpsURLConnection访问了
 * </pre>
 * @author elvis.xu
 * @since 2013-12-13
 */
public class HttpsTest {

	public static void main(String[] args) throws IOException {
//		URL url = new URL("http://localhost/xjd-note/auth/input");
//		URL url = new URL("https://localhost/xjd-note/");
//		URL url = new URL("https://localhost/xjd-note/auth/input?locale=en_US");
//		URL url = new URL("https://blockchain.info/zh-cn/merchant/8e32db72-a1cc-4909-ae2e-72ee090bb73a/balance?password=elvis9xuqq1015012955");
		URL url = new URL("https://blockchain.info/zh-cn/merchant/8e32db72-a1cc-4909-ae2e-72ee090bb73a/list?password=elvis9xuqq1015012955");
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

}
