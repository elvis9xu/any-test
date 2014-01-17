package com.xjd.test.threadexception;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * <pre>
 * 为Thread设置UncaughtExceptionHandler,处理未捕获的RuntimeException
 * 也可以把Thread加入到ThreadGroup, 设置ThreadGroup的UncaughtExceptionHandler
 * 最后都没有时会调用Thread.getDefaultUncaughtExceptionHandler()来处理
 * </pre>
 * @author elvis.xu
 * @since Jan 17, 2014 1:38:28 PM
 */
public class ThreadExceptionCaught {
	public static void main(String[] args) {
		Thread t = new MyThread();
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		t.start();
	}
	
	public static class MyThread extends Thread {

		@Override
		public void run() {
			throw new RuntimeException("Uncaught Exception!");
		}
		
	}
	
	public static class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.err.println("Caught Exception for Thread[" + t + "]:");
			e.printStackTrace();
		}
		
	}
}
