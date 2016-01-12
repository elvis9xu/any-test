package com.xjd.test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

public class GenericTypeTest {
	public List<? super A> list;

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		Field field = GenericTypeTest.class.getField("list");
		Type type = field.getGenericType();
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			for (Type t : parameterizedType.getActualTypeArguments()) {
				System.out.println(t.getClass());
				if (t instanceof WildcardType) {
					WildcardType wt = (WildcardType) t;
					System.out.println("upper ======");
					for (Type it : wt.getUpperBounds()) {
						System.out.println(it);
					}
					System.out.println("lower ======");
					for (Type it : wt.getLowerBounds()) {
						System.out.println(it);
					}
				}
			}
		}
	}

	public static class A {}

	public static class B extends A {}

	public static class C extends B {}
	
	public static interface D {}

}
