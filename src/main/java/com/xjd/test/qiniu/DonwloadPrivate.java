package com.xjd.test.qiniu;

import java.io.UnsupportedEncodingException;

import com.qiniu.common.Zone;
import com.qiniu.util.Auth;

/**
 * @author elvis.xu
 * @since 2017-12-15 15:54
 */
public class DonwloadPrivate {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String domain = "http://p0zsq946a.bkt.clouddn.com";
		String key = "XmeAyc_wb8vfTea3oJinUvG7CiVkaSUOaGp4PL1z";
		String secret = "0qXo4I1FzUq4iLdPtvuO3NAyguDfnBGXFfWVF2fI";
		String bucket = "only-private";
		Zone zone = Zone.zone0(); // 华东

		String filename = "文件名2.txt";
//		String pubUrl = domain + "/" + URLEncoder.encode(filename, "utf-8");
		String pubUrl = domain + "/" + filename;

		Auth auth = Auth.create(key, secret);
		String privateUrl = auth.privateDownloadUrl(pubUrl, 3600);// 1h

		System.out.println(privateUrl);

	}
}
