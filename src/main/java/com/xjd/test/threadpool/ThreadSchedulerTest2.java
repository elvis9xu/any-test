package com.xjd.test.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author elvis.xu
 * @since 2017-09-01 15:30
 */
public class ThreadSchedulerTest2 {
	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

		scheduledExecutorService.schedule(() ->{
			System.out.println("我是schedule1");
		}, 0, TimeUnit.SECONDS);

		Thread.sleep(20000L);

	}
}
