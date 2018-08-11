package com.xjd.test.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.xjd.utils.basic.JsonUtils;

/**
 * 用户授权
 * @author elvis.xu
 * @since 2018-01-08 18:16
 */
public class AlipayUserAuthTest extends AlipayPassword {
	public static final String CHARSET = "UTF-8";
	public static final String URL = "https://openapi.alipay.com/gateway.do";

	public static void main(String[] args) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET,
				ALIPAY_PUBLIC_KEY, "RSA2");

//		oauthToken(alipayClient);
		getUserInfo(alipayClient);
	}

	// 使用auth_code换取token
	//{
	//  "alipay_system_oauth_token_response": {
	//    "access_token": "authusrBa65f61777f2e4c84976c2b5a40638X63",
	//    "alipay_user_id": "20880066742887062761928823116363",
	//    "expires_in": 1296000,
	//    "re_expires_in": 2592000,
	//    "refresh_token": "authusrBd6ec31a0df224ed7a67de6ba30d7dA63",
	//    "user_id": "2088421605759638"
	//  },
	//  "sign": "Xsi48kpM6MmRGxZrIQ/xYZEsDheMhcWt5PFYu6IcnqlsgZzn8Y6G/+5f2Kk1KOrQtrSpH2WAIJj55vGwWKVSaCy9a5natM4ua
	// /Y42T6PzK/R4dHI5yIJMT1v4tHn/tp0SugWgfi6KUWc+6qX03sFjycqTe4oeYnW0/p6MifU0q+Xh2zBqedeYF
	// /nWnaijX5MNgWDaMVP0FEbJFUnYrt0Sdp18X6KXxf+hM074Gi9CnJMHRVlZLbeO7UtfihBGFYocNAjOJmq
	// +VrDdU9WKEUZ96D6HQq3eZlw3UfteniKpj4GmeaV4VhM2b7yDOrZDOSZNhXLysFJuV4UlqStA+kPZA=="
	//}
	public static void oauthToken(AlipayClient alipayClient) throws AlipayApiException {
		AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
		request.setCode("62a92d303cd54deba4719cb06d7fXD79");
		request.setGrantType("authorization_code");

		AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
		System.out.println(JsonUtils.toJson(response));
	}

	// 获取用户信息
	// {
	//  "alipay_user_info_share_response": {
	//    "code": "10000",
	//    "msg": "Success",
	//    "city": "珠海市",
	//    "is_certified": "T",
	//    "is_student_certified": "F",
	//    "province": "广东省",
	//    "user_id": "2088421605759638",
	//    "user_status": "T",
	//    "user_type": "1"
	//  },
	//  "sign": "VMqC00Skn/khXqZR0/WhRwD09fxIR4AMiFwKulJwudDjrSPrOINkyjzMYIki+WD74XBpsabB4LvJkmT5o
	// /HJjhJIAoy7yJGeOQCIaxC2J6XRCotHRvJ98TMlX1lERgBnynUdVAL5Axv3XB6ZAf9HW/D6swiVHOjudpN8pWTziesx
	// /n73FdwJGRjJyox48kvaQAfqiN8rdJdiNB9giOWR4SndAGOLg5ayM7xB8Rtp17+asRWMdI9Vxgn3N8PoQYkR
	// /v3QD2AkVhJA7ZJ3OJvEsrocvGBLxgBDQdqeLpCs0g1Okn6i9iJ2V9WST2wHGZhbQ2l4mPSfnItMl85k1T+qRQ=="
	// }
	public static void getUserInfo(AlipayClient alipayClient) throws AlipayApiException {
		AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
		String token = "authusrB2f623702a3f048d2849f0d95d558cX79";

		AlipayUserInfoShareResponse response = alipayClient.execute(request, token);
		System.out.println(JsonUtils.toJson(response));
	}
}
