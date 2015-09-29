package com.xjd.test.bean;

import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjd.test.bean.example.TestBean;

public abstract class BeanGuardTest {

	public static void guard(Object bean, String... guardGroups) {
		if (bean == null) return;

		Class clazz = bean.getClass();

		System.out.println("====getFields");
		for (Field f : clazz.getFields()) {
			System.out.println("[" + f.getName() + "]");
		}

		printDeclaredFields(clazz);
		System.out.println("=======end=============");
	}

	public static void printDeclaredFields(Class clazz) {
		System.out.println("=====" + clazz.getName() + ".getDeclaredFields()======");
		for (Field f : clazz.getDeclaredFields()) {
			System.out.println("[" + f.getName() + "]");
		}
		if (clazz.getSuperclass() != null) {
			printDeclaredFields(clazz.getSuperclass());
		}
	}

	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		{
			TestBean bean1 = new TestBean();
			BeanGuard.guard(bean1);
			System.out.println(mapper.writeValueAsString(bean1));
		}
		{
			TestBean bean = new TestBean();
			BeanGuard.guard(bean, "A");
			System.out.println(mapper.writeValueAsString(bean));
		}
		{
			TestBean bean = new TestBean();
			BeanGuard.guard(bean, "B");
			System.out.println(mapper.writeValueAsString(bean));
		}
		{
			TestBean bean = new TestBean();
			BeanGuard.guard(bean, "A", "C");
			System.out.println(mapper.writeValueAsString(bean));
		}

	}
}
