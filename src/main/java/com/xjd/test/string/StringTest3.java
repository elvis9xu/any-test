package com.xjd.test.string;

import org.apache.commons.lang3.StringUtils;

/**
 * @author elvis.xu
 * @since 2017-09-25 10:35
 */
public class StringTest3 {
	public static void main(String[] args) {
		String s = "20170924171933111中国社会福利基金会免费午餐基金@maillist.sendcloud.org";


//		System.out.println(StringUtils.substring(s, 0, 10));
		System.out.println(StringUtils.substring(s, -5, -2));


	}

}
