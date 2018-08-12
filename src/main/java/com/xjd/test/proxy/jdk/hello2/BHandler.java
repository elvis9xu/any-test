package com.xjd.test.proxy.jdk.hello2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author elvis.xu
 * @since 2018-08-11 12:22
 */
public class BHandler implements InvocationHandler {
	Object target;

	public BHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(method);
		return method.invoke(target, args);
	}

	public Object getProxy() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces
				(), this);
	}
}
