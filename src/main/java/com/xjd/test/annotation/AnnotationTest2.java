package com.xjd.test.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * Java注解也可以当接口用，其实现类可以作为这类注解的默认配置类
 * </pre>
 * @author elvis.xu
 * @since 2015-9-20
 */
public class AnnotationTest2 {

	public static void main(String[] args) {
		HelloAnno hello = new Hello();
		System.out.println(hello.value());
	}

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface HelloAnno {
		String[] value();
	}

	public static class Hello implements HelloAnno {

		@Override
		public Class<? extends Annotation> annotationType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] value() {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
