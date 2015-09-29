package com.xjd.test.perf4j;

import org.perf4j.log4j.Log4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Perf4jTest {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Perf4jTest.class);
	private static Logger log2 = LoggerFactory.getLogger(Perf4jTest.class);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			log.info("log4j1 start...");
			Log4JStopWatch stopWatch = new Log4JStopWatch("hello");

			Thread.sleep(1000);

			stopWatch.stop("hello", "HAHA");
		}
	}

}
