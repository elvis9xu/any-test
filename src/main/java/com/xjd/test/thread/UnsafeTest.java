package com.xjd.test.thread;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * @author elvis.xu
 * @since 2015-04-14 21:32
 */
public class UnsafeTest {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
		Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");

		theUnsafe.setAccessible(true);

		Unsafe unsafe = (Unsafe) theUnsafe.get(null);

		System.out.println(unsafe);

		Field ageField = PrivateClass.class.getDeclaredField("age");
		long ageOffset = unsafe.objectFieldOffset(ageField);

		PrivateClass privateObj = (PrivateClass) unsafe.allocateInstance(PrivateClass.class);
		System.out.println(privateObj);

		System.out.println(privateObj.getAge());

		unsafe.putInt(privateObj, ageOffset, 20);
		System.out.println(privateObj.getAge());
	}

	public static class PrivateClass {

		private int age = 10;

		private PrivateClass() {
			age = 20;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}
}
