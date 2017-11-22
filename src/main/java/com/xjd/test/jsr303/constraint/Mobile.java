package com.xjd.test.jsr303.constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;

/**
 * @author elvis.xu
 * @since 2017-08-24 14:17
 */


@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MobileValidator.class})
public @interface Mobile {
	String message() default "手机";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
