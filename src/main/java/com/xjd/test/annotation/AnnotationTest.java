package com.xjd.test.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotationTest {

	@HelloAnnotation("HELLO")
	public static interface Hello {
		
	}
	
	@HelloClassAnnotation("HELLOIMPL")
	public static class HelloImpl implements Hello {
		
	}
	
	public static class HelloImpl2 extends HelloImpl {
		
	}
	
	@Inherited
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface HelloAnnotation {
		String value();
	}
	
	@Inherited
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface HelloClassAnnotation {
		String value();
	}
	
	public static void main(String[] args) {
		{
			System.out.println("===========Hello Annotions===========");
			Annotation[] anns = Hello.class.getAnnotations();
			for (Annotation ann : anns) {
				System.out.println(ann);
			}
		}
		{
			System.out.println("===========HelloImpl Annotions===========");
			Annotation[] anns = HelloImpl.class.getAnnotations();
			for (Annotation ann : anns) {
				System.out.println(ann);
			}
		}
		{
			System.out.println("===========HelloImpl2 Annotions===========");
			Annotation[] anns = HelloImpl2.class.getAnnotations();
			for (Annotation ann : anns) {
				System.out.println(ann);
			}
		}
	}
}
