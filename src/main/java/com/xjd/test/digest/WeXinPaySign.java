package com.xjd.test.digest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author elvis.xu
 * @since 2017-05-23 16:50
 */
public class WeXinPaySign {

	public static void main(String[] args) {
		System.out.println(md5("appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA&key=192006250b4c09247ec02edce69f6a2d").toUpperCase());
		System.out.println("9A0A8659F005D6984697E2CA0A9CF3B7");
		System.out.println("===========orderSign");
		orderSign();
		System.out.println("===========paySign");
		paySign();
		System.out.println("===========configSign");
		configSign();
		System.out.println("===========orderQuerySign");
		orderQuerySign();
		System.out.println("===========closeOrderSign");
		closeOrderSign();
	}

	public static void orderSign() {
		Map<String, String> param = new LinkedHashMap<>();
		param.put("appid", "wxcd3f03135dd1b9e8");
		param.put("mch_id", "1465168202");
		param.put("nonce_str", "bf50b2791f0c4e13b535fa0fa9dfd8d2");
		param.put("sign_type", "MD5");
		param.put("body", "罗辑思维-图书");
		param.put("out_trade_no", "ORD0000000010");
		param.put("total_fee", "1");
		param.put("spbill_create_ip", "118.178.132.84");
		param.put("notify_url", "https://test-api.wozai4u.com/manage/resource/api/test/wechat/raw1");
		param.put("trade_type", "JSAPI");
//		param.put("openid", "oRCA30U2kfRJryECMYy8p-ISIXUI");
		param.put("openid", "oRCA30YI0P0SuiZLr7zPLJyiUwNQ"); // ME

		String ps = sortToString(param) + "&key=XXXXXYYYYYZZZZZXXXXXYYYYYZZZZZAB";
		System.out.println(ps);
		String sign = md5(ps).toUpperCase();
		System.out.println(sign);

		param.put("sign", sign);

		String fs = param.entrySet().stream().map(e -> "<" + e.getKey() + ">" + e.getValue() + "</" + e.getKey() + ">").collect(Collectors.joining("\r\n", "<xml>\r\n", "\r\n</xml>"));
		System.out.println(fs);
	}

	public static void paySign() {
		Map<String, String> param = new LinkedHashMap<>();
		param.put("appId", "wxcd3f03135dd1b9e8");
		param.put("timeStamp", "1495526210");
		param.put("nonceStr", "bf50b2791f0c4e13b535fa0fa9dfd8d2");
		param.put("package", "prepay_id=wx20170531163155ffaa0622e40211879356");
		param.put("signType", "MD5");

		String ps = sortToString(param) + "&key=XXXXXYYYYYZZZZZXXXXXYYYYYZZZZZAB";
		System.out.println(ps);
		String sign = md5(ps).toUpperCase();
		System.out.println(sign);
		param.put("paySign", sign);

		String fs = param.entrySet().stream().map(e -> "\"" + ("timeStamp".equals(e.getKey()) ? "timestamp" : e.getKey()) + "\": \"" + e.getValue() + "\"").collect(Collectors.joining(",\r\n"));
		System.out.println(fs);
	}

	public static void orderQuerySign() {
		Map<String, String> param = new LinkedHashMap<>();
		param.put("appid", "wxcd3f03135dd1b9e8");
		param.put("mch_id", "1465168202");
		param.put("out_trade_no", "ORD0000000003");
		param.put("nonce_str", "bf50b2791f0c4e13b535fa0fa9dfd8d2");
		param.put("sign_type", "MD5");

		String ps = sortToString(param) + "&key=XXXXXYYYYYZZZZZXXXXXYYYYYZZZZZAB";
		System.out.println(ps);
		String sign = md5(ps).toUpperCase();
		System.out.println(sign);
		param.put("sign", sign);

		String fs = param.entrySet().stream().map(e -> "<" + e.getKey() + ">" + e.getValue() + "</" + e.getKey() + ">").collect(Collectors.joining("\r\n", "<xml>\r\n", "\r\n</xml>"));
		System.out.println(fs);
	}

	public static void closeOrderSign() {
		Map<String, String> param = new LinkedHashMap<>();
		param.put("appid", "wxcd3f03135dd1b9e8");
		param.put("mch_id", "1465168202");
		param.put("out_trade_no", "ORD0000000010");
		param.put("nonce_str", "bf50b2791f0c4e13b535fa0fa9dfd8d2");
		param.put("sign_type", "MD5");

		String ps = sortToString(param) + "&key=XXXXXYYYYYZZZZZXXXXXYYYYYZZZZZAB";
		System.out.println(ps);
		String sign = md5(ps).toUpperCase();
		System.out.println(sign);
		param.put("sign", sign);

		String fs = param.entrySet().stream().map(e -> "<" + e.getKey() + ">" + e.getValue() + "</" + e.getKey() + ">").collect(Collectors.joining("\r\n", "<xml>\r\n", "\r\n</xml>"));
		System.out.println(fs);
	}

	public static void configSign() {
		Map<String, String> param = new LinkedHashMap<>();
		param.put("jsapi_ticket", "HoagFKDcsGMVCIY2vOjf9utetqxWN6xHX5NaYNZtyTk2KXKXclxlYs0-IAcX5qOxKO6fBNx6k6kOgTGhqOZnKA");
		param.put("noncestr", "bf50b2791f0c4e13b535fa0fa9dfd8d2");
		param.put("timestamp", "1495526210");
		param.put("url", "http://xjd.tunnel.2bdata.com/1.html");

		String ps = sortToString(param);
		System.out.println(ps);
		String sign = sha1(ps);
		System.out.println(sign);

//		String fs = param.entrySet().stream().map(e -> "\"" + ("timeStamp".equals(e.getKey()) ? "timestamp" : e.getKey()) + "\": \"" + e.getValue() + "\"").collect(Collectors.joining(",\r\n"));
//		System.out.println(fs);
	}

	public static String sortToString(Map<String, ?> map) {
		String txt = map.entrySet().stream().sorted((o1, o2) -> o1.getKey().compareTo(o2.getKey())).map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
		return txt;
	}

	public static String sha1(String src) {
		return digest(src, "SHA-1");
	}
	public static String md5(String src) {
		return digest(src, "MD5");
	}

	public static String digest(String src, String alg) {
		try {
			MessageDigest crypt = MessageDigest.getInstance(alg);
			crypt.reset();
			crypt.update(src.getBytes("UTF-8"));
			return byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

}
