package com.xjd.test.reference;

/**
 * @author elvis.xu
 * @since 2017-10-10 15:17
 */
public class FinalizeTest2 {
	public static void main(String[] args) throws InterruptedException {

		ThreadLocal tl = new ThreadLocal();

		Thread t = new Thread() {
			@Override
			public void run() {
				tl.set(new Object() {
					@Override
					protected void finalize() throws Throwable {
						System.out.println("finalize");
						super.finalize();
					}
				});
			}
		};

		t.start();
		t.join();
		t = null;

		System.gc();
		Thread.sleep(5000L);
	}
}
