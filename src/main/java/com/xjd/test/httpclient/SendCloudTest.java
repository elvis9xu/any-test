package com.xjd.test.httpclient;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author elvis.xu
 * @since 2017-08-14 16:16
 */
public class SendCloudTest {
	public static void main(String[] args) {
		// 使用HttpPost提交文件上传表单示例

		CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建HttpClient
		HttpPost httpPost = new HttpPost("http://localhost:8080/api/test/raw"); // 创建HttpPost
//		HttpPost httpPost = new HttpPost("http://api.sendcloud.net/apiv2/mail/send"); // 创建HttpPost

		HttpEntity httpEntity = MultipartEntityBuilder.create()
				// 注意解决中文乱码
				.setLaxMode() // 使用浏览器兼容模式, 否则中文会乱码
				.setContentType(ContentType.MULTIPART_FORM_DATA.withCharset(Charset.forName("utf8"))) // 一定要设置字符集，否则中文会乱码
				.addPart("apiUser", new StringBody("accessTest01", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))))
				.addPart("apiKey", new StringBody("Ms7JQn6v35shk5w3", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))))
				.addPart("from", new StringBody("hello@wozai4u.com", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))))
				.addPart("fromName", new StringBody("您好", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))))
				.addPart("replyTo", new StringBody("elvis9xu@wozai4u.com", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))))
				.addPart("to", new StringBody("TEST1@maillist.sendcloud.org", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))))
				.addPart("subject", new StringBody("中文好了吧", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))))
				.addPart("html", new StringBody("<p>你牛B</p><a href=\"http://www.baidu.com\">百度</a>", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))))
				.addPart("labelId", new StringBody("136003", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))))
				.addPart("headers", new StringBody("{\"SC-Custom--LabelID\":\"136003\"}", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))))
				.addPart("useAddressList", new StringBody("true", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))))
				.addPart("attachments", new FileBody(new File("/Users/XJD/Downloads/联系人导入模板-请重命名.xlsx"), ContentType.APPLICATION_OCTET_STREAM.withCharset(Charset.forName("utf8"))))
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


public static void testHttpPostMultipartForm() {
	// 使用HttpPost提交文件上传表单示例

	CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建HttpClient
	HttpPost httpPost = new HttpPost("http://localhost:8080/api/test/test2"); // 创建HttpPost

	HttpEntity httpEntity = MultipartEntityBuilder.create()
			.setLaxMode() // 使用浏览器兼容模式，否则中文会乱码
			.setContentType(ContentType.MULTIPART_FORM_DATA.withCharset(Charset.forName("utf8"))) // 一定要设置字符集，否则中文会乱码
			.addTextBody("time", "123456", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf8"))) // 最好指定一下ContentType哦
			.addBinaryBody("file", "我是文件内容".getBytes(Charset.forName("utf8")), ContentType.APPLICATION_OCTET_STREAM.withCharset(Charset.forName("utf8")), "中文名会乱码哦.txt") // 中文名乱码问题在上面步骤设置后解决
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
}
