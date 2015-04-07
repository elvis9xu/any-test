package com.xjd.test.param;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	public static String MD5For32(String paramString) {
		if (paramString == null)
			return "";
		MessageDigest localMessageDigest;
		byte[] arrayOfByte1;
		try {
			localMessageDigest = MessageDigest.getInstance("MD5");
			char[] arrayOfChar = paramString.toCharArray();
			arrayOfByte1 = new byte[arrayOfChar.length];
			for (int i = 0; i < arrayOfChar.length; i++)
				arrayOfByte1[i] = ((byte) arrayOfChar[i]);
		} catch (Exception localException) {
			localException.printStackTrace();
			return "";
		}
		byte[] arrayOfByte2 = localMessageDigest.digest(arrayOfByte1);
		StringBuffer localStringBuffer = new StringBuffer();
		for (int j = 0; j < arrayOfByte2.length; j++) {
			int k = 0xFF & arrayOfByte2[j];
			if (k < 16)
				localStringBuffer.append("0");
			localStringBuffer.append(Integer.toHexString(k));
		}
		return localStringBuffer.toString();
	}

	public static String Md5(String paramString) {
		if ((paramString != null) && (!paramString.trim().equals("")))
			;
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			char[] arrayOfChar = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
			byte[] arrayOfByte = localMessageDigest.digest(paramString.getBytes("UTF8"));
			StringBuilder localStringBuilder = new StringBuilder();
			for (int i = 0; i < arrayOfByte.length; i++) {
				localStringBuilder.append(arrayOfChar[((0xF0 & arrayOfByte[i]) >>> 4)]);
				localStringBuilder.append(arrayOfChar[(0xF & arrayOfByte[i])]);
			}
			String str = localStringBuilder.toString();
			paramString = str;
			return paramString;
		} catch (Exception localException) {
			return paramString;
		} 
	}
}
