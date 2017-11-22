package com.xjd.test.valid;

import java.io.*;
import java.util.regex.Pattern;

/**
 * @author elvis.xu
 * @since 2017-10-12 13:22
 */
public class EmailTest2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("/Users/XJD/Desktop/1.sql.csv"));

		Pattern mailPattern = Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");

		String line = null;
		while ((line = br.readLine()) != null) {
			if (!mailPattern.matcher(line).matches()) {
				System.out.println(line);
			}
		}

		br.close();
	}
}
