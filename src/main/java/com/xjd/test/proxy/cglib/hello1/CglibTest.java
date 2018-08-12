package com.xjd.test.proxy.cglib.hello1;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

import lombok.val;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author elvis.xu
 * @since 2018-08-11 13:00
 */
public class CglibTest {
	public static void main(String[] args) throws SQLException {
		val enhance = new Enhancer();
		enhance.setSuperclass(NoDefaultConstructor.class);
		enhance.setInterfaces(new Class[]{Connection.class});
		enhance.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println(obj);
				System.out.println(proxy);
				System.out.println(method.getDeclaringClass());
				System.out.println(method);
				return null;
			}
		});

		Object proxy = enhance.create(new Class[]{String.class}, new Object[]{"XXX"});

		System.out.println(((Connection) proxy).getAutoCommit());
		System.out.println(proxy.toString());
	}

	public static class NoDefaultConstructor {
		private String name;

		public NoDefaultConstructor(String name) {
			this.name = name;
		}
	}

}
