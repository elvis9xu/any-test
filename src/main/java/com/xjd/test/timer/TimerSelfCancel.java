package com.xjd.test.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerSelfCancel {

	public static void main(String[] args) {
		final Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			public void run() {
				System.out.println("task begin...");
				timer.cancel();
				System.out.println("timer canceled");
			}
		};
		
		timer.schedule(task, 1000L, 1000L);
		
		
		timer.schedule(task, 1000L, 1000L);
		
	}
}
