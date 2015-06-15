package com.xjd.test.regex;

import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
//		String pattern = "^((13[0-9])|(14[5,7])|(15[^4,\\D])|(170)|(18[^4,\\D]))\\d{8}$";
		String pattern = "^((13[0-9])|(15[^4,\\D])|(18[,0-9])|(17[0-9])|(3[0-9]{2}))\\d{8}$";
		System.out.println(Pattern.matches(pattern, "32300000000"));

//		String source = "[53209]用户密码错误";
//		String rt = source.replaceFirst("\\[\\d+\\]", "");
//		System.out.println(rt);
	}

}
