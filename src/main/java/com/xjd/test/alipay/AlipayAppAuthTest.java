package com.xjd.test.alipay;

import lombok.val;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppQueryRequest;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.xjd.utils.basic.JsonUtils;

/**
 * 第三方托管授权
 * @author elvis.xu
 * @since 2018-01-08 18:16
 */
public class AlipayAppAuthTest extends AlipayPassword {
	public static final String CHARSET = "UTF-8";
	public static final String URL = "https://openapi.alipay.com/gateway.do";

	public static void main(String[] args) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET,
				ALIPAY_PUBLIC_KEY, "RSA2");

//		oauthToken(alipayClient);
		authInfo(alipayClient);
	}

	// 使用app_auth_code换取token
	// {
	//  "alipay_open_auth_token_app_response": {
	//    "code": "10000",
	//    "msg": "Success",
	//    "app_auth_token": "201805BBcdc3a2adac8f469b8c716c77c1a6cX63",
	//    "app_refresh_token": "201805BBe46b79cf87c840189faf2afa0609bA63",
	//    "auth_app_id": "2016081001726593",
	//    "expires_in": 31536000,
	//    "re_expires_in": 32140800,
	//    "tokens": [
	//      {
	//        "app_auth_token": "201805BBcdc3a2adac8f469b8c716c77c1a6cX63",
	//        "app_refresh_token": "201805BBe46b79cf87c840189faf2afa0609bA63",
	//        "auth_app_id": "2016081001726593",
	//        "expires_in": 31536000,
	//        "re_expires_in": 32140800,
	//        "user_id": "2088421605759638"
	//      }
	//    ],
	//    "user_id": "2088421605759638"
	//  },
	//  "sign": "w58O7oJYOWTctAN8ieFNQw3IBKxua7Z
	// /5TjQXhhOSx68PdwFrjn3btmazDytW3fRNnaWvY5uoFgCuqCggONbE99PbIn2ezmUc5X11U2iWBHgALTZr9
	// +4ri3OIUIi51r4Xhs5wy7UAB5hDfMqGgWXHOiexUR8EWEwuLz6cobLqF9XGqchHfUghkWab+bsuwl8cojMgyFRTSlTPGyHoM
	// /SV2eGvGRGFUkGIw6xqrrSJClz9PYepdo19MWuAPh8XPLvE3gkFqiVaAUbUJzX1Mk5F/o6TIo5z
	// /L2R9ED8fZumJFdB9aIJTh3izgaeEEOqhPaXmbzXOVjQmx8GaZVKWeZ+g=="
	//}
	public static void oauthToken(AlipayClient alipayClient) throws AlipayApiException {
		val request = new AlipayOpenAuthTokenAppRequest();

		request.setBizContent("{\n" + "  \"grant_type\": \"authorization_code\",\n" + "  \"code\": " +
				"\"05c884e0a89148419f63298d269d5X79\"\n" + "}");

		val response = alipayClient.execute(request);
		System.out.println(JsonUtils.toJson(response));
	}

	// 查询授权消息
	//{
	//  "alipay_open_auth_token_app_query_response": {
	//    "code": "10000",
	//    "msg": "Success",
	//    "auth_app_id": "2016081001726593",
	//    "auth_end": "2019-05-03 15:58:47",
	//    "auth_methods": [
	//      "alipay.system.oauth.token",
	//      "alipay.trade.order.settle",
	//      "alipay.trade.cancel",
	//      "alipay.trade.precreate",
	//      "alipay.data.dataservice.bill.downloadurl.query",
	//      "alipay.user.userinfo.share",
	//      "alipay.trade.page.pay",
	//      "alipay.trade.pay",
	//      "alipay.trade.wap.pay",
	//      "alipay.trade.create",
	//      "alipay.trade.fastpay.refund.query",
	//      "alipay.trade.close",
	//      "alipay.user.info.share",
	//      "monitor.heartbeat.syn",
	//      "koubei.trade.order.consult",
	//      "alipay.open.auth.token.app",
	//      "alipay.trade.query",
	//      "alipay.trade.refund",
	//      "alipay.user.info.auth",
	//      "alipay.open.auth.token.app.query",
	//      "alipay.trade.app.pay"
	//    ],
	//    "auth_start": "2018-05-03 15:58:47",
	//    "expires_in": 31536000,
	//    "status": "valid",
	//    "user_id": "2088421605759638"
	//  },
	//  "sign": "w3u5S6owUjj6aPbOLgWu5dTny7AepqL4XRRcaBQbY+CXOXzXkc/G5AL+lkJ6OQwX0wUYevI
	// /or7i8rjgDbRmlKrkqkmsoGQ2hG26R7SBLKoNPGiaqi6L9Oa6
	// +Ao3wEAAJfqmyUxVvn8qGhoZhcQRihBE81SyjByF36juoWaMiIzIezvPFKvnYXj
	// /ctEnHdkFDiVJzdAGNDJFWEfLBWf8KCbVoZbmTqWZoccdvk7DkumDpY41eBJLjdV8Seh9/yA5EYatB
	// /EC8pSpruvHH8aIaLoVGaCHLTGNN7mx78zQWAvS5Rxx+KccF1z4OF6wn3OIS46tq1iCxDljUIYi0Xlz6A=="
	//}
	public static void authInfo(AlipayClient alipayClient) throws AlipayApiException {
		val request = new AlipayOpenAuthTokenAppQueryRequest();
		request.setBizContent("{\n  \"app_auth_token\": \"201805BBdf45ff5134964218acf0b82ded9cfC79\"\n}");

		val response = alipayClient.execute(request);
		System.out.println(JsonUtils.toJson(response));
	}
}
