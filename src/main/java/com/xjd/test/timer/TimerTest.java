package com.xjd.test.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		
//		timer.schedule(new MyTimerTask(), 1000L);
		timer.schedule(new MyTimerTask(), 1000L, 1000L);
		
		Thread.sleep(10000);
	}

	public static class MyTimerTask extends TimerTask {

		@Override
		public void run() {
			System.out.println("ONE");
		}

	}
}
