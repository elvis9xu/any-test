package com.xjd.test.httpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author elvis.xu
 * @since 2017-08-10 16:05
 */
public class HttpClientTest {
	public static void main(String[] args) {
		testHttpPostMultipartForm();
	}

	public static void testHttpGet() {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建HttpClient
		// 或者使用HttpClientBuilder自定义参数
		// CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(10).build();

		// 使用URLEncodedUtils生成参数字符串，也可在地址后面直接拼接
		List<NameValuePair> params = Arrays.asList(new BasicNameValuePair("time", "123456"), new BasicNameValuePair("geoType", "0"));
		String paramStr = URLEncodedUtils.format(params, Charset.forName("utf8"));

		HttpGet httpGet = new HttpGet("http://localhost:8080/api/config/geo?" + paramStr); // 创建HttpRequest

		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet); // 使用HttpClient执行HttpRequest得到HttpResponse

			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) { // 判断HttpResponse状态
				System.out.println("应答状态非成功: " + httpResponse.getStatusLine());
			} else {
				System.out.println("应答成功!");
				System.out.println(EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf8"))); // 获取HttpResponse的body
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testHttpPostForm() {
		// 使用HttpPost提交普通表单示例

		CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建HttpClient
		HttpPost httpPost = new HttpPost("http://localhost:8080/api/test/test2"); // 创建HttpPost

		List<NameValuePair> params = Arrays.asList(new BasicNameValuePair("time", "123456"));
		HttpEntity httpEntity = new UrlEncodedFormEntity(params, Charset.forName("utf8")); // 创建HttpEntity

		httpPost.setEntity(httpEntity); // 把HttpEntity设置到HttpPost中

		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost); // 使用HttpClient执行HttpRequest得到HttpResponse

			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) { // 判断HttpResponse状态
				System.out.println("应答状态非成功: " + httpResponse.getStatusLine());
			} else {
				System.out.println("应答成功!");
				System.out.println(EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf8"))); // 获取HttpResponse的body
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testHttpPostMultipartForm() {
		// 使用HttpPost提交文件上传表单示例

		CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建HttpClient
//		HttpPost httpPost = new HttpPost("http://localhost:8971/api/test/raw"); // 创建HttpPost
		HttpPost httpPost = new HttpPost("http://localhost:8080/api/test/raw"); // 创建HttpPost

		HttpEntity httpEntity = MultipartEntityBuilder.create()
				.setLaxMode()
				.setContentType(ContentType.MULTIPART_FORM_DATA.withCharset(Charset.forName("utf8")))
				.addTextBody("time", "123456", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))) // 最好指定一下ContentType哦
				.addBinaryBody("file", "我是文件内容".getBytes(Charset.forName("utf8")), ContentType.APPLICATION_OCTET_STREAM.withCharset(Charset.forName("utf8")), "中文名会乱码哦.txt") // 中文件名会乱码没有解决方案，可以使用UrlEncod一下
				.addPart("file", new FileBody(new File("/Users/XJD/Downloads/联系人导入模板-请重命名.xlsx")))
				.build(); // 通过MultipartEntityBuilder创建Multipart的请求体，这里还可以继续添加要上传的内容

		httpPost.setEntity(httpEntity); // 把HttpEntity设置到HttpPost中

		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost); // 使用HttpClient执行HttpRequest得到HttpResponse

			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) { // 判断HttpResponse状态
				System.out.println("应答状态非成功: " + httpResponse.getStatusLine());
			} else {
				System.out.println("应答成功!");
				System.out.println(EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf8"))); // 获取HttpResponse的body
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testHttpPostJson() {
		// 使用HttpPost提交Json数据示例

		CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建HttpClient
		HttpPost httpPost = new HttpPost("http://localhost:8080/api/test/test2"); // 创建HttpPost

		String jsonTxt = "{\"code\":\"0000\",\"msg\":\"成功\",\"time\":1502354481500}";
		// 创建HttpEntity, 这里使用了ContentType工具类来构建带字符集的Mime哦, 仅仅是为了演示
		StringEntity httpEntity = new StringEntity(jsonTxt, ContentType.APPLICATION_JSON.withCharset(Charset.forName("utf8")));

		httpPost.setEntity(httpEntity); // 把HttpEntity设置到HttpPost中

		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost); // 使用HttpClient执行HttpRequest得到HttpResponse

			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) { // 判断HttpResponse状态
				System.out.println("应答状态非成功: " + httpResponse.getStatusLine());
			} else {
				System.out.println("应答成功!");
				System.out.println(EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf8"))); // 获取HttpResponse的body
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testConfig() {
		// 使用HttpClient访问Https(SSL证书)资源示例
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		try {
			File pk12 = new File("/Users/XJD/Downloads/cer2/wo.p12");
			String pk12Pass = "123456";

			File jks = new File("/Users/XJD/Downloads/cer2/wo.jks");
			String jkspass = "123456";

			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(new FileInputStream(pk12), pk12Pass.toCharArray());
			KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("sunx509");
			keyManagerFactory.init(keyStore, pk12Pass.toCharArray());

			KeyStore trustKeyStore = KeyStore.getInstance("jks");
			trustKeyStore.load(new FileInputStream(jks), jkspass.toCharArray());
			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("sunx509");
			trustManagerFactory.init(trustKeyStore);

			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

			httpClientBuilder.setSSLContext(sslContext); // 设置自定义的SSLContext
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		}

		CloseableHttpClient httpClient =  httpClientBuilder.build();

		List<NameValuePair> params = Arrays.asList(new BasicNameValuePair("time", "123456"), new BasicNameValuePair("geoType", "0"));
		String paramStr = URLEncodedUtils.format(params, Charset.forName("utf8"));

		HttpGet httpGet = new HttpGet("https://test-api.wozai4u.com/saas/api/config/geo?" + paramStr);
//		HttpGet httpGet = new HttpGet("https://www.baidu.com?" + paramStr);

		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) { // 判断HttpResponse状态
				System.out.println("应答状态非成功: " + httpResponse.getStatusLine());
			} else {
				System.out.println("应答成功!");
				System.out.println(EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf8"))); // 获取HttpResponse的body
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
