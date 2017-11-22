package com.xjd.test.reference;

/**
 * @author elvis.xu
 * @since 2017-10-10 15:17
 */
public class FinalizeTest {
	public static void main(String[] args) throws InterruptedException {

		Object a = new Object() {
			@Override
			protected void finalize() throws Throwable {
				System.out.println("finalize");
				super.finalize();
			}
		};

		a = null;
		System.gc();
		Thread.sleep(5000L);
	}
}
