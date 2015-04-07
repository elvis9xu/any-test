package com.xjd.test.regex;

public class RegexTest {

	public static void main(String[] args) {
//		String pattern = "^((13[0-9])|(14[5,7])|(15[^4,\\D])|(170)|(18[^4,\\D]))\\d{8}$";
//		System.out.println(Pattern.matches(pattern, "18488888888"));

		String source = "[53209]用户密码错误";
		String rt = source.replaceFirst("\\[\\d+\\]", "");
		System.out.println(rt);
	}

}
