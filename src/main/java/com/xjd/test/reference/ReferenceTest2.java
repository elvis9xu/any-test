package com.xjd.test.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author elvis.xu
 * @since 2017-10-10 11:50
 */
public class ReferenceTest2 {
	public static void main(String[] args) throws InterruptedException {
		ReferenceQueue referenceQueue = new ReferenceQueue();
		WeakReference r = new WeakReference(new Object(), referenceQueue) {
			@Override
			public boolean enqueue() {
				System.out.println("inqueue");
				return super.enqueue();
			}
		};

		System.out.println(r.get());
		System.gc();
		System.out.println(r.get());
		Reference poll = referenceQueue.poll();
		if (poll != null) {
			System.out.println(poll.get());
		}
	}
}
