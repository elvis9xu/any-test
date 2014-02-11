package com.xjd.test.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class InputStreamFeatureInterceptor implements MethodInterceptor {
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Class mc = method.getDeclaringClass();
		System.out.println(mc + "--" + method);
		return null;
	}

	
}