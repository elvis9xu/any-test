package com.xjd.test.qiniu;


import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.xjd.utils.basic.JsonUtils;

/**
 * @author elvis.xu
 * @since 2017-12-15 16:05
 */
public class UploadFile {
	public static void main(String[] args) throws QiniuException {
		String key = "XmeAyc_wb8vfTea3oJinUvG7CiVkaSUOaGp4PL1z";
		String secret = "0qXo4I1FzUq4iLdPtvuO3NAyguDfnBGXFfWVF2fI";
		String bucket = "only-private";
		Zone zone = Zone.zone0(); // 华东

		Configuration cfg = new Configuration(zone);
		UploadManager uploadManager = new UploadManager(cfg);

		Auth auth = Auth.create(key, secret);
		String upToken = auth.uploadToken(bucket);

		Response response = uploadManager.put("我是文件内容".getBytes(Charset.forName("utf8")), "文件名2.txt", upToken);
		DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

		System.out.println(JsonUtils.toJson(response));
		System.out.println(JsonUtils.toJson(putRet));
	}
}
