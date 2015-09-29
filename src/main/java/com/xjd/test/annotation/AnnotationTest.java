package com.xjd.test.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * <pre>
 * @Inherited:
 * 		父子类之间可以继承
 * 		接口和实现类之间不可以继承
 * 		方法重写后不能继承(不重写时获取的就是父类的方法,所以不存在继不继承的关系)
 * </pre>
 * @author elvis.xu
 * @since 2015-9-20
 */
public class AnnotationTest {

	@HelloAnnotation("HELLO")
	public static interface Hello {
		@HelloAnnotation("method")
		public void hello();
	}

	@HelloClassAnnotation("HELLOIMPL")
	@HelloAnnotation("HELLO2")
	public static class HelloImpl implements Hello {

		@Override
		@HelloAnnotation("method2")
		public void hello() {}

	}

	public static class HelloImpl2 extends HelloImpl {

//		@Override
//		public void hello() {
//			// TODO Auto-generated method stub
//			super.hello();
//		}

	}

	public static class HelloImpl3 implements Hello {

		@Override
		public void hello() {

		}

	}

	@Inherited
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = { ElementType.TYPE, ElementType.METHOD })
	public static @interface HelloAnnotation {
		String value();
	}

//	@Inherited
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface HelloClassAnnotation {
		String value();
	}

	public static void main(String[] args) throws SecurityException, NoSuchMethodException {

		{
			System.out.println("===========Hello Annotions===========");
			Annotation[] anns = Hello.class.getAnnotations();
			for (Annotation ann : anns) {
				System.out.println(ann);
			}
			Method helloM = Hello.class.getMethod("hello", null);
			for (Annotation ann : helloM.getAnnotations()) {
				System.out.println(ann);
			}
		}
		{
			System.out.println("===========HelloImpl Annotions===========");
			Annotation[] anns = HelloImpl.class.getAnnotations();
			for (Annotation ann : anns) {
				System.out.println(ann);
			}
			Method helloM = HelloImpl.class.getMethod("hello", null);
			for (Annotation ann : helloM.getAnnotations()) {
				System.out.println(ann);
			}
		}
		{
			System.out.println("===========HelloImpl2 Annotions===========");
			Annotation[] anns = HelloImpl2.class.getAnnotations();
			for (Annotation ann : anns) {
				System.out.println(ann);
			}
			Method helloM = HelloImpl2.class.getMethod("hello", null);
			for (Annotation ann : helloM.getAnnotations()) {
				System.out.println(ann);
			}
		}
		{
			System.out.println("===========HelloImpl3 Annotions===========");
			Annotation[] anns = HelloImpl3.class.getAnnotations();
			for (Annotation ann : anns) {
				System.out.println(ann);
			}
			Method helloM = HelloImpl3.class.getMethod("hello", null);
			for (Annotation ann : helloM.getAnnotations()) {
				System.out.println(ann);
			}
		}
	}
}
