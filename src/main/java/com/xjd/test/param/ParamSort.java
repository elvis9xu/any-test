package com.xjd.test.param;

import java.util.Arrays;

public class ParamSort {

	public static void main(String[] args) {
		String paramStr = "macid=8345b17a70e11dd189d0587ce07dcbb8&app=a-ajk&v=4.4.2&qtime=20150402110802&pm=b95&o=dior-user+4.4.2+KVT49L+KHICNBH21.0+release-keys&uuid=48d88f01-a33d-4fed-858f-932015040210&from=mobile&m=Android-HM+NOTE+1LTEW&cv=8.5&cid=11&i=865199023674173&commid=3345&rsl=65535";

		String[] paramPairs = paramStr.split("&");
		Arrays.sort(paramPairs);

		System.out.println(paramStr);
		StringBuilder sb = new StringBuilder();
		for (String paramPair : paramPairs) {
			sb.append("&");
			sb.append(paramPair);
			System.out.println(paramPair);
		}
		sb.deleteCharAt(0);

		System.out.println(sb.toString());
	}

}
