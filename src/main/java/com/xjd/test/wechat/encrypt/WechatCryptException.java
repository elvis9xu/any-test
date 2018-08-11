package com.xjd.test.wechat.encrypt;

import org.jetbrains.annotations.NonNls;

/**
 * @author elvis.xu
 * @since 2018-04-03 13:49
 */
public class WechatCryptException extends RuntimeException {
	public WechatCryptException() {
	}

	public WechatCryptException(@NonNls String message) {
		super(message);
	}

	public WechatCryptException(String message, Throwable cause) {
		super(message, cause);
	}

	public WechatCryptException(Throwable cause) {
		super(cause);
	}

	public WechatCryptException(String message, Throwable cause, boolean enableSuppression, boolean
			writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
