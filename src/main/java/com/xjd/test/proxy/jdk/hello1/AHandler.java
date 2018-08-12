package com.xjd.test.proxy.jdk.hello1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author elvis.xu
 * @since 2018-08-11 11:30
 */
public class AHandler implements InvocationHandler {
	Object target;

	public AHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("method=" + method + ", args=" + Arrays.toString(args));

		return method.invoke(target, args);
	}

	public Object getProxy() {
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces
				(), this);
	}
}
