package com.xjd.test.reflect;

import java.lang.reflect.Method;

public class IntegrationClassAndInterface extends AClass implements AInterface {

	@Override
	public void aMethod2() {
		// TODO Auto-generated method stub
		super.aMethod2();
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param args
	 * @author elvis.xu
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @since 2014-2-11
	 */
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		Method m = IntegrationClassAndInterface.class.getMethod("aMethod", null);
		System.out.println(m.getDeclaringClass());
		
		Method m2 = IntegrationClassAndInterface.class.getMethod("aMethod2", null);
		System.out.println(m2.getDeclaringClass());
	}
}
