package com.xjd.test.proxy.cglib.hello2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.val;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author elvis.xu
 * @since 2018-08-11 13:00
 */
public class CglibTest2 {
	public static void main(String[] args) throws SQLException {
		final ObjectA a = new ObjectA("xxx");
		ObjectB b = new ObjectB();

		val enhance = new Enhancer();
		enhance.setSuperclass(ObjectA.class);
		enhance.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				if (method.getDeclaringClass().equals(Object.class)) {
//					return proxy.invoke(a, args);
					return proxy.invokeSuper(obj, args);
				}

				System.out.println(obj);
				System.out.println(method);
				System.out.println(method.getDeclaringClass());
				System.out.println(proxy);
				System.out.println(obj.getClass());
				return proxy.invoke(a, args);
			}
		});

		Object proxy = enhance.create(new Class[]{String.class}, new Object[]{"yyy"});

		((ObjectA) proxy).sayHello();

		try {
			Constructor<ObjectB> constructor = ObjectB.class.getConstructor(null);
			System.out.println(constructor);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		Enhancer enhancer2 = new Enhancer();
		enhancer2.setSuperclass(PreparedStatement.class);
//		enhancer2.setInterfaces(new Class[] {PreparedStatement.class});
		enhancer2.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				return null;
			}
		});
		Object proxy2 = enhancer2.create();
		System.out.println(proxy2.getClass());

	}

	public static class ObjectA {
		private String name;

		public ObjectA(String name) {
			this.name = name;
		}

		public void sayHello() {
			System.out.println("hello Object A: " + name);
			sayHello2();
		}

		public void sayHello2() {
			System.out.println("hello Object A2: " + name);
		}
	}

	public static class ObjectB {
		private String name;

		public void sayHello() {
			System.out.println("hello Object B");
		}
	}



}
