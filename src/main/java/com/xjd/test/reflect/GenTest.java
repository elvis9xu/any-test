package com.xjd.test.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author elvis.xu
 * @since 2015-12-24 20:04
 */
public class GenTest<T extends GenTest.A> {

	private static List<String> list;

	private static void method(List<Integer> list) {

	}

	public static void main(String[] args) throws NoSuchFieldException {
		for (Method method : GenTest.class.getDeclaredMethods()) {
			for (Type type : method.getGenericParameterTypes()) {
				System.out.println("1:" + type);
				if (type instanceof ParameterizedType) {
					ParameterizedType pt = (ParameterizedType) type;
					System.out.println("2" + pt.getActualTypeArguments()[0]);
				}
			}
		}
	}

	public static class A {

	}
	public static class B extends  A {

	}

	public static class C extends  B {

	}
}
