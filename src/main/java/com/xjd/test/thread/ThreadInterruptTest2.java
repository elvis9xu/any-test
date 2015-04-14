package com.xjd.test.thread;

public class ThreadInterruptTest2 {

	public static void main(String[] args) {
		
		System.out.println(Thread.interrupted());
		
		Thread.currentThread().interrupt();
		
		System.out.println(Thread.interrupted());
		
	}

}
