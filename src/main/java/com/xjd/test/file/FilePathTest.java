package com.xjd.test.file;

import java.io.File;
import java.io.IOException;

public class FilePathTest {

	public static void main(String[] args) throws IOException {
		File test = new File("D:/test");
		System.out.println(test.getPath());
		System.out.println(test.getAbsolutePath());
		System.out.println(test.getCanonicalPath());
		
		File test2 = new File("D:/test/");
		System.out.println(test2.getPath());
		System.out.println(test2.getAbsolutePath());
		System.out.println(test2.getCanonicalPath());
		
		for (File f : test.listFiles()) {
			System.out.println(f.getPath());
		}
	}

}
