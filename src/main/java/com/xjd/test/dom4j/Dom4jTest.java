package com.xjd.test.dom4j;

import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * @author elvis.xu
 * @since 2017-06-05 16:01
 */
public class Dom4jTest {
	public static void main(String[] args) throws DocumentException {
		String txt = "<xml><return_code><![CDATA[SUCCESS]]></return_code>\n" +
				"<return_msg><![CDATA[OK]]></return_msg>\n" +
				"<appid><![CDATA[wxcd3f03135dd1b9e8]]></appid>\n" +
				"<mch_id><![CDATA[1465168202]]></mch_id>\n" +
				"<nonce_str><![CDATA[3Poj4iOFYY6BRa0V]]></nonce_str>\n" +
				"<sign><![CDATA[3CC53DA369030EA76C5786513878F16A]]></sign>\n" +
				"<result_code><![CDATA[FAIL]]></result_code>\n" +
				"<err_code><![CDATA[ORDERPAID]]></err_code>\n" +
				"<err_code_des><![CDATA[该订单已支付]]></err_code_des>\n" +
				"</xml>";

		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new StringReader(txt));

		Node node = document.selectSingleNode("//xxx");
		System.out.println(node.getStringValue());
	}
}
