/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2014 ChinaPnR,Inc.All Rights Reserved.
 */
package com.xjd.test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author elvis.xu
 * @version $Id: ThreadPoolExecutorTest.java, v 0.1 2014-2-10 上午10:23:21 elvis.xu Exp $
 */
public class ThreadPoolExecutorTest {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService es1 = new ThreadPoolExecutor(1, 2, 2, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(3));
//        ExecutorService es2 = new ThreadPoolExecutor(0, 2, 60, TimeUnit.SECONDS,
//            new SynchronousQueue<Runnable>());

        Runnable t1 = new Task("Task\t1");
        Runnable t2 = new Task("Task\t2");
        Runnable t3 = new Task("Task\t3");
        Runnable t4 = new Task("Task\t4");
        Runnable t5 = new Task("Task\t5");

        es1.submit(t1);
        es1.submit(t2);
        es1.submit(t3);
        es1.submit(t4);
        es1.submit(t5);
//        es2.submit(t3);
//        es2.submit(t4);
        
        
    }

    public static class Task implements Runnable {

        String msg;

        Task(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            int i = 0;
            while (i < 5) {
                System.out.println(msg);
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                }
                i++;
            }
        }

    }

}
