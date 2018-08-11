package com.xjd.test.wechat.encrypt;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.qq.weixin.mp.aes.AesException;

/**
 * @author elvis.xu
 * @since 2018-04-03 11:53
 */
public class WechatCrypt {
	Charset CHARSET = StandardCharsets.UTF_8;
	byte[] aesKey;
	String token;
	String appId;

	public WechatCrypt(String appId, String token, String encodingAesKey) {
		if (encodingAesKey.length() != 43) {
			throw new WechatCryptException("Invalid AES key!");
		}

		this.token = token;
		this.appId = appId;
		this.aesKey = Base64.decodeBase64(encodingAesKey + "=");
	}

	/**
	 * 对密文进行解密.
	 *
	 * @param text 需要解密的密文
	 * @return 解密得到的明文
	 * @throws AesException aes解密失败
	 */
	public String decrypt(String text) {
		byte[] original;
		try {
			// 设置解密模式为AES的CBC模式
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec key_spec = new SecretKeySpec(aesKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
			cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

			// 使用BASE64对密文进行解码
			byte[] encrypted = Base64.decodeBase64(text);

			// 解密
			original = cipher.doFinal(encrypted);
		} catch (Exception e) {
			throw new WechatCryptException("Decrypt AES error!", e);
		}

		String xmlContent, from_appid;
		try {
			// 去除补位字符
			byte[] bytes = PKCS7Encoder.decode(original);

			// 分离16位随机字符串,网络字节序和AppId
			byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

			int xmlLength = recoverNetworkBytesOrder(networkOrder);

			xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
			from_appid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length),
					CHARSET);
		} catch (Exception e) {
			throw new WechatCryptException("Illegal Buffer!", e);
		}

		// appid不相同的情况
		if (!from_appid.equals(appId)) {
			throw new WechatCryptException("Invalid AppId !");
		}
		return xmlContent;

	}

	// 生成4个字节的网络字节序
	byte[] getNetworkBytesOrder(int sourceNumber) {
		byte[] orderBytes = new byte[4];
		orderBytes[3] = (byte) (sourceNumber & 0xFF);
		orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
		orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
		orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
		return orderBytes;
	}

	// 还原4个字节的网络字节序
	int recoverNetworkBytesOrder(byte[] orderBytes) {
		int sourceNumber = 0;
		for (int i = 0; i < 4; i++) {
			sourceNumber <<= 8;
			sourceNumber |= orderBytes[i] & 0xff;
		}
		return sourceNumber;
	}
}
