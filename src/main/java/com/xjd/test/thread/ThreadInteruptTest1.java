package com.xjd.test.thread;

public class ThreadInteruptTest1 {

	public static void main(String[] args) {
		Thread t = new MyThread();
		t.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.interrupt();
		
		System.out.println("=================");
		System.out.println(t.isAlive());
		System.out.println(t.isAlive());
		
		System.out.println(t.isInterrupted());
		System.out.println(t.isInterrupted());
	}

	public static class MyThread extends Thread {
		@Override
		public void run() {
			for (;;) {
				System.out.print(Thread.interrupted());
//				for (int i = 0; i < 10000000; i++) {}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
