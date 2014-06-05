package com.xjd.test.thread;

public class InheritableThreadLocalTest {
	public static ThreadLocal<String> sThreadLocal = new ThreadLocal<String>();
	public static InheritableThreadLocal<String> sInheritableThreadLocal = new InheritableThreadLocal<String>();

	public static void main(String[] args) throws InterruptedException {
		sThreadLocal.set("HELLO");
		sInheritableThreadLocal.set("WORLD");

		new Thread() {
			@Override
			public void run() {
				System.out.println("Thread: " + sThreadLocal.get());
				System.out.println("Thread: " + sInheritableThreadLocal.get());
				sInheritableThreadLocal.set("WORLD2");
				System.out.println("Thread: " + sInheritableThreadLocal.get());
			}

		}.start();
		
		Thread.sleep(100);
		System.out.println("Main: " + sThreadLocal.get());
		System.out.println("Main: " + sInheritableThreadLocal.get());
	}

}
