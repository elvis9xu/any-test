package com.xjd.test.cglib;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;

public class CglibTest {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param args
	 * @author elvis.xu
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @since 2014-2-11
	 */
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		Enhancer eh = new Enhancer();
		eh.setSuperclass(NullInputStream.class);
		eh.setInterfaces(new Class[]{InputStreamFeature.class});
		eh.setCallback(new InputStreamFeatureInterceptor());
		Object proxy = eh.create();
		
		try {
			((InputStream) proxy).close();
		} catch (IOException e) {
		}
		
		try {
			((InputStreamFeature) proxy).close();
		} catch (IOException e) {
		}
		
		Method m = InputStreamFeature.class.getMethod("hehe", null);
		
		try {
			m.invoke(proxy, null);
		} catch (Exception e) {
		}
		
		try {
			m.invoke(new NullInputStream(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected static class NullInputStream extends FilterInputStream {
		public NullInputStream() {
			super(null);
		}
	}

}
