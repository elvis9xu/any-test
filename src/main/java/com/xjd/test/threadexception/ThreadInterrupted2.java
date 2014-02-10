/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2014 ChinaPnR,Inc.All Rights Reserved.
 */
package com.xjd.test.threadexception;

import java.io.FileOutputStream;

/**
 * 
 * @author elvis.xu
 * @version $Id: ThreadInterrupted2.java, v 0.1 2014-2-10 下午1:45:23 elvis.xu Exp $
 */
public class ThreadInterrupted2 {

    public static void main(String[] args) {
        Thread t = new MyThread();
        t.start();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.interrupt();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class MyThread extends Thread {

        public MyThread() {
            super("TASK");
        }

        @Override
        public void run() {
            try {
                FileOutputStream fout = new FileOutputStream("D:/test/tmp.out");
                while (true) {
                   fout.write(1);
                }
            } catch (Exception e) {
                System.out.println("HELLO");
                e.printStackTrace();
            }
        }

    }

}
