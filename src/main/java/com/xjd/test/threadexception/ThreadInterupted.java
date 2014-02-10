package com.xjd.test.threadexception;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadInterupted {

    public static void main(String[] args) {
        //        for (int i = 0; i < 1000; i ++) {
        //            new MockThread("NAME " + i).start();
        //        }
        MockThread t = new MockThread("NAME " + 1);
        ExecutorService es = Executors.newCachedThreadPool();
        final Future f = es.submit(t);

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    f.get(2L, TimeUnit.SECONDS);
                    System.out.println("GET!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }

        });
        t2.start();
        //        try {
        //            Thread.sleep(1000L);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //        t2.interrupt();
        es.shutdownNow();
    }

    public static class MockThread extends Thread {

        public MockThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            long count = 0;
            for (int i=0; i<10000000L; i++) {
                count = (count + i - i) * i;
                System.out.print(i);
            }
        }

    }

}
