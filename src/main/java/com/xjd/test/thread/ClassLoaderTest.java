package com.xjd.test.thread;

public class ClassLoaderTest {

	public static void main(String[] args) {
		System.out.println("================Hierachy==============");
		ClassLoader cl1 = ClassLoaderTest.class.getClassLoader();
		System.out.println(cl1);
		
		ClassLoader cl2 = cl1.getParent();
		System.out.println(cl2);
		
		ClassLoader cl3 = cl2.getParent();
		System.out.println(cl3);
		
		
		System.out.println("================System==============");
		ClassLoader cl4 = ClassLoader.getSystemClassLoader();
		System.out.println(cl4);
		
	}

}
