package com.xjd.test.alipay;

import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

/**
 * @author elvis.xu
 * @since 2018-01-08 18:16
 */
public class AlipayTest {
	public static final String CHARSET = "UTF-8";
	public static final String APP_ID = "2018010501607274";
	public static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCbh1dt0SeKFwAv5G8aRIsWbyw538n6gYQLImTO9gD3yM1LnXoUv6xKNZ5jrlWAgpIwB8Sk8igRH0RUM8zdJRv4cqour/H8xGlH+J+brqZhLzbOKdHBqh0gEE0WLariu71gGPb2r0D/UMwQPQcY/Jm11+h6aZdqjDBeXM5KelGbbRNELorVmLVcdLiCOd4tsvrkp8MGmjC5pu5Pb98mZsSz7ls4TfJnNQks43m0M7rQkr2YrQJpg6nw67QlhPlX+soVan0qkKUR9Trne11kJ9mWGpx1uDeL4h6G3foVoQ1wr0O+r5xmy1Ly6x78eGtmUo/GfTov5P4ZHD/ss7L8N4B5AgMBAAECggEAQVGCAnkI5hHq3jJOBvYqg+2E4Xl/Vln2nsZ/F/xxQ59tuQgWMYieTzxdnPemqSYtR0a765pYAXjkNYjbzTS8nodpudnjA4lpvfRI9BVRgxdVKRifI3rSaJjwBxR+5GJ8klNpxQitf4zrZxZ6WKAd3iUjWeXm1bSyy66mZNkiVOe+cHhdkrfwGoRMELMU3xo2hrjvyfQVb+mv1rzxa/CeUK9Sy8gIrnsoq9t2Fk6PTJsULFYFvCUpd8+VLnsXiPF4UK53oDo0pC93LChmkcJkToi9kLtshTnFvBueBNvncUVRZJ32MPjj7YsAf/NsDMsEa3Sd0e1AWrXxKQIAyQpuAQKBgQDUwqiwzaVypFM4X4zY9h2ysLgQP3R3J6hFLsoCOBKB+dD+vi/CW2A59K25+6+IcygVJyQ6FBxv34FjHMeUCj3athuBa4T6ORb/B5cK6Wnn3EtlJNYawjQvsB5zFeTZBScIRYr+3asEKl5qlwjAhOyH1fJGVw4dLzv493tB7P+WiQKBgQC7IxLC0whBK8tGbEXp7SWLKUOIVq5hZafnSpHFe/uLq/ZDyC69Niu5wxdCxg120+0WbHRDkegynkJsu1pqI8zFrxXCzJuja9wVAAC4ePJ+fyqWgcwTexVelPWHEaZJ8z6xUTEqBwhglNieayEOQARI/HnAoX0VmDq4lp5mnTIecQKBgGVzVKDJzoR12sDcvvqNkOet4HbO6ygOHuyuPOEF/70+c+ukoH1auO2pwFUYsECuOIStn1aaVFz8BGRQStJKd+gcQc1aiZQPOOnuopUva7KFluXpxLYJuHyszDgwTmk1TcFh979Dz9IyqcbFsgSq3WaAHABybmZ4KW60A3ASJGWRAoGBAKJLb6jhJmaG2ZswqY2PqqC+35+tNTyo0e4IQNXZibV/JGtyf/5+rXtEU7cIVoL4lsR8uHQlZRAJKqhr76TfeWb0SlYP5uba0xqvTvCti/jixoeS9+7pLwZA/5LtXp8Bwxw6wv2k7cH9NMRnOlrGcDDwLZM/OmDxDfQqRCPQLrgxAoGAFR2Wrs9MiLO6IgGRzpgNW56+Wpw/IjT3ebOfcVXlqBuhIVOgZJFQ0Ds/25Q7UPsdd8dfQbXW1BnF35kl+Y/OvVoAXGjDC2DezmvbwOm8/XaACJcTfhqx3LpCRJs0doIq8m2Nvem4iIhAWKz/doww+XZ0J/ZNoSu2NUTA5SZO8Fw=";
	public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyiW5fDH2JNFz1t2mdD7my5mEhCkpsCdDycymQDXEoiZ10pK4N4FD1jJB6Yr+j4cQVLXEyj2zJUDekDIcjmSXwhQtYjFiIL0oOZHiTg/9b4dviKFr3G1LLu4IFtDTslXsXlVw/xUTlzYKKAanQOy/UuVYb6vPJDCw4TooLI1l1cpGD2zLswnWY46g66MwIe9zONOraBkvFTM3wPJ2V0GSJ6TwQ6KNJH8CCwKYc//lltU+FzZXtKTMGkrFKP0hv/2dWZ9fwPmDIuiQmywsKia1tbQlPO1xlz7CWuhF5MEIq1N2ZwSyt1U7UIdcEBDxuuS9CxtUCIWDypnGxIEAsDDfOQIDAQAB";
	public static final String URL = "https://openapi.alipay.com/gateway.do";

	public static void main(String[] args) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET,
				ALIPAY_PUBLIC_KEY, "RSA2");
		pagePay(alipayClient);
	}

	// 发起支付
	public static void pagePay(AlipayClient alipayClient) {
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		request.setReturnUrl("http://test-saas.wozai4u.com");
		request.setNotifyUrl("http://test.vvaccess.com:9021/api/test/callback");
		request.setBizContent("{" +
				"\"out_trade_no\":\"TEST201801010009\"," +
				"\"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
				"\"total_amount\":0.01," +
				"\"subject\":\"好东西哦!\"," +
				"\"body\":\"就是好东西哦!\"," +
				"\"passback_params\":\"Type%3d3C%26merchantBizNo%3d2016010101111\"," +
				"\"timeout_express\":\"1m\"," +
				"\"qr_pay_mode\":\"4\"," + // 扫码方式
				"\"qrcode_width\":\"100\"" + // 扫码方式
				"}");

		try {
			System.out.println(alipayClient.pageExecute(request).getBody());
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}

	// 关闭支付
	public static void closePay(AlipayClient alipayClient) {
		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
		request.setNotifyUrl("http://test.vvaccess.com:9021/api/test/callback");
		request.setBizContent("{" +
				"\"out_trade_no\":\"TEST201801010003\"" +
				"}");

		AlipayTradeCloseResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

		if (response.isSuccess()) {
			System.out.println(response.getBody());
		} else {
			System.out.println(response);
		}
	}

	// 查询结果
	public static void tradeQuery(AlipayClient alipayClient) {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent("{" +
				"\"out_trade_no\":\"TEST201801010001\"" +
				"}");

		AlipayTradeQueryResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

		if (response.isSuccess()) {
			System.out.println(response.getBody());
		} else {
			System.out.println(response);
		}
	}



	// 退款
	public static void tradeRefund(AlipayClient alipayClient) {
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent("{" +
				"\"out_trade_no\":\"TEST201801010001\"," +
				"\"refund_amount\":0.01," +
				"\"refund_reason\":\"测试退款\"" +
				"}");

		AlipayTradeRefundResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

		if (response.isSuccess()) {
			System.out.println(response.getBody());
		} else {
			System.out.println(response);
		}
	}

	// 退款查询
	public static void tradeRefundQuery(AlipayClient alipayClient) {
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		request.setBizContent("{" +
				"\"out_trade_no\":\"TEST201801010001\"," +
				"\"out_request_no\":\"TEST201801010001\"" +
				"}");

		AlipayTradeFastpayRefundQueryResponse response = null;
		try {
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

		if (response.isSuccess()) {
			System.out.println(response.getBody());
		} else {
			System.out.println(response);
		}
	}

	// 异步通知验签
	public static void verifySign(AlipayClient alipayClient) throws AlipayApiException {
		Map<String, String> map = new HashMap<>();

//		map.put("app_id", "2018010501607274");
//		map.put("auth_app_id", "2018010501607274");
//		map.put("charset", "UTF-8");
//		map.put("method", "alipay.trade.page.pay.return");
//		map.put("out_trade_no", "TEST201801010002");
//		map.put("seller_id", "2088421605759638");
//		map.put("timestamp", "2018-01-09 10:46:53");
//		map.put("total_amount", "0.01");
//		map.put("trade_no", "2018010921001004790560253125");
//		map.put("version", "1.0");
//		map.put("sign", "C0LxJekDVru08HboiRKDI5MGQRI110cnojdnTqNlOM6b9+0tr8gd/dqKSBDtIiPnf0i6ub/YvS5aOjQBQaWW3kiOXsoB10M3pUA5tGwCFSC62fGvqSwuNapZwzJKTIWNAyMnmbLYzfGmV1oa5zuc8P06EBPqUhpsIWqNcGKl13wO6gjo0r7zVMvxzxEw3duLz2i4WB/3cSb0d1VPs+fjYWibxyZwxfs5sKhOUIpneXS7MZiCKvwh/Qwk2cJ4grBGh8Kx5sMt0MneowOaOvyglp5fCR/oxQEpJ3zYD1CDoKnZq7yS7UuXulL7cakgDmd3dwKov98j+dc+uSqWebz2nw==");
//		map.put("sign_type", "RSA2");

		map.put("total_amount", "0.01");
		map.put("timestamp", "2018-01-09 14:34:26"); // 要把+号替换为空格，我晕，垃圾支付宝
		map.put("sign", "yh3gImTJOqTrj0IgDw+i2eSM/2gi7vGOtKMPNCzmRtFZM1n6cKId0hnTet4lmJ22dwdnaF++0gL0NRgtIMfR0CG1T27cH+G0xXVJgzVy/KsIp0+4T20TDI1jqNOU9tDvsgQpjqKEFRSI7oJV6cTUNXhNA8I1ZKa9dI2sehiO54+KXPSvg6D1FOPJHXROtSMQgLOZ1ExpGb7j91we+xnkNl7wmy3KmZYYjf5nLCBMNU/e6G8Tw7JOB/SD9KN+YsAQ/NCeB04np0cNnqj52iNJcuax75i2vstAsF7363tIYf5daDb3HCVwlJELifuTXJtZPIf7VrVxA7tbOqV1IJYiOQ==");
		map.put("trade_no", "2018010921001004790559849725");
		map.put("sign_type", "RSA2");
		map.put("auth_app_id", "2018010501607274");
		map.put("charset", "UTF-8");
		map.put("seller_id", "2088421605759638");
		map.put("method", "alipay.trade.page.pay.return");
		map.put("app_id", "2018010501607274");
		map.put("out_trade_no", "TEST201801010005");
		map.put("version", "1.0");

		boolean v = AlipaySignature.rsaCheckV1(map, ALIPAY_PUBLIC_KEY, CHARSET, "RSA2");
		System.out.println("verified: " + v);
	}
}
