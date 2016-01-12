package com.xjd.test.threadpool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

public class ThreadScheduler {

	public static void main(String[] args) {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(200);
		for (int i = 0; i < 200; i++) {
			executor.schedule(new TestTask(StringUtils.leftPad(i+"", 3)), 2, TimeUnit.SECONDS);
		}
		System.out.println("===============================================");
	}

	public static class TestTask implements Runnable {
		private String name;

		public TestTask(String name) {
			super();
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println(name + ": start");
			try {
				Thread.sleep(200L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + ": end");
		}

	}
}
