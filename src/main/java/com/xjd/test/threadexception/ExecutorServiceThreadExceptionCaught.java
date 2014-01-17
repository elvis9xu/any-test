package com.xjd.test.threadexception;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <pre>
 * ExecutorService中的Thread有异常时,会在future.get()中抛出
 * </pre>
 * @author elvis.xu
 * @since Jan 17, 2014 1:44:48 PM
 */
public class ExecutorServiceThreadExceptionCaught {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<?> future = executor.submit(new MyRunnable());
		
		try {
			future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public static class MyRunnable implements Runnable {

		@Override
		public void run() {
			throw new RuntimeException("Uncaught Exception!");
		}
		
	}
	
}
