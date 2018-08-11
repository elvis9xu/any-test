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
 * 手机网站支付
 * @author elvis.xu
 * @since 2018-01-08 18:16
 */
public class AlipayMobileWebTest extends AlipayPassword {
	public static final String CHARSET = "UTF-8";
	public static final String APP_ID = "2017110309695803";
	public static final String URL = "https://openapi.alipay.com/gateway.do";

	public static void main(String[] args) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET,
				ALIPAY_PUBLIC_KEY, "RSA2");
		wapPay(alipayClient);
	}

	// 发起支付
	public static void wapPay(AlipayClient alipayClient) {
		AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
		request.setReturnUrl("http://test-saas.wozai4u.com");
//		request.setNotifyUrl("http://test.vvaccess.com:9021/api/test/callback");
//		request.setNotifyUrl("http://vip.xxx.vvaccess.com");
		request.setNotifyUrl("http://test-vip.wozai4u.com/");
		request.setBizContent("{" +
				"\"out_trade_no\":\"MOBILE2018032011\"," +
				"\"product_code\":\"QUICK_WAP_PAY\"," +
				"\"total_amount\":0.01," +
				"\"subject\":\"好东西哦!\"," +
				"\"body\":\"就是好东西哦!\"," +
				"\"passback_params\":\"Type%3d3C%26merchantBizNo%3d2016010101111\"," +
				"\"timeout_express\":\"30m\"" +
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
//				"\"out_trade_no\":\"TEST201801010010\"" +
				"\"out_trade_no\":\"TEST2018010100XX\"" +
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
				"\"out_trade_no\":\"TEST201801010010\"," +
				"\"out_request_no\":\"TEST201801010010R4\"," +
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
				"\"out_trade_no\":\"TEST201801010010\"," +
				"\"out_request_no\":\"TEST201801010010R3\"" +
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

		map.put("gmt_create","2018-01-09 10:46:34");// 要把+号替换为空格，我晕，垃圾支付宝
		map.put("charset","UTF-8");
		map.put("subject","好东西哦!");
		map.put("sign","t2JUnUGuXY7gom+Xn14RsFXlsFoExWiIIAY0RTvXQwH4wOK9p112Hagg8bSsV0vpa7UCybYkCv9xlALzekl3a8QAsir+oQ9ZsFMoTbum0mTqYFU8zz6y4RmS8jM3J/8OYN0pb4Bysse0HDEgYJLLCHq3RSQlU5XrFfJwO/N+Hm/tGKMB7pQc+IWFKa0bzXG1Zy485kJ4ZLwkMQBv18Z2CHhWJGOSnTBkXgOVs+HBsaoAiGgRsN/t8ZPGCJlDsTjQKAv5gfAkdTlXsyb8ZDoV2DRBLZaPbZgzS/kSb+tbe28dDKhSnLQUbq97iHZe2Rn84iKSRm4TwcxPmOeNpM57jw==");
		map.put("buyer_id","2088502067530792");
		map.put("body","就是好东西哦!");
		map.put("invoice_amount","0.01");
		map.put("notify_id","67768d31ed093013faf33e50b1b5dd9m3l");
		map.put("fund_bill_list","[{\"amount\":\"0.01\",\"fundChannel\":\"ALIPAYACCOUNT\"}]");
		map.put("notify_type","trade_status_sync");
		map.put("trade_status","TRADE_SUCCESS");
		map.put("receipt_amount","0.01");
		map.put("app_id","2018010501607274");
		map.put("buyer_pay_amount","0.01");
		map.put("sign_type","RSA2");
		map.put("seller_id","2088421605759638");
		map.put("gmt_payment","2018-01-09 10:46:42");// 要把+号替换为空格，我晕，垃圾支付宝
		map.put("notify_time","2018-01-09 10:46:43");// 要把+号替换为空格，我晕，垃圾支付宝
		map.put("passback_params","Type%3d3C%26merchantBizNo%3d2016010101111");
		map.put("version","1.0");
		map.put("out_trade_no","TEST201801010002");
		map.put("total_amount","0.01");
		map.put("trade_no","2018010921001004790560253125");
		map.put("auth_app_id","2018010501607274");
		map.put("point_amount","0.00");

//		map.put("total_amount", "0.01");
//		map.put("timestamp", "2018-01-09 14:34:26"); // 要把+号替换为空格，我晕，垃圾支付宝
//		map.put("sign", "yh3gImTJOqTrj0IgDw+i2eSM/2gi7vGOtKMPNCzmRtFZM1n6cKId0hnTet4lmJ22dwdnaF++0gL0NRgtIMfR0CG1T27cH+G0xXVJgzVy/KsIp0+4T20TDI1jqNOU9tDvsgQpjqKEFRSI7oJV6cTUNXhNA8I1ZKa9dI2sehiO54+KXPSvg6D1FOPJHXROtSMQgLOZ1ExpGb7j91we+xnkNl7wmy3KmZYYjf5nLCBMNU/e6G8Tw7JOB/SD9KN+YsAQ/NCeB04np0cNnqj52iNJcuax75i2vstAsF7363tIYf5daDb3HCVwlJELifuTXJtZPIf7VrVxA7tbOqV1IJYiOQ==");
//		map.put("trade_no", "2018010921001004790559849725");
//		map.put("sign_type", "RSA2");
//		map.put("auth_app_id", "2018010501607274");
//		map.put("charset", "UTF-8");
//		map.put("seller_id", "2088421605759638");
//		map.put("method", "alipay.trade.page.pay.return");
//		map.put("app_id", "2018010501607274");
//		map.put("out_trade_no", "TEST201801010005");
//		map.put("version", "1.0");

		boolean v = AlipaySignature.rsaCheckV1(map, ALIPAY_PUBLIC_KEY, CHARSET, "RSA2");
		System.out.println("verified: " + v);
	}
}
