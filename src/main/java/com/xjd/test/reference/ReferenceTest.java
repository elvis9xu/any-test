package com.xjd.test.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author elvis.xu
 * @since 2017-10-10 11:50
 */
public class ReferenceTest {
	public static void main(String[] args) throws InterruptedException {
//		Object a = new Object();
		Object b = new Object();

//		SoftReference<Object> as = new SoftReference<Object>(a);
		WeakReference<Object> bw = new WeakReference<Object>(b, new ReferenceQueue());

//		a = null;
		b = null;

//		System.out.println(as.get());
		System.out.println(bw.get());
		System.out.println(bw.isEnqueued());
		System.out.println(bw.get());
		System.out.println(bw.isEnqueued());

		System.gc();
		Thread.sleep(2000L);
		System.out.println(bw.get());
		System.out.println(bw.isEnqueued());
	}
}
