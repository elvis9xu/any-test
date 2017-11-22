package com.xjd.test.jsr303.constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

import static java.lang.annotation.ElementType.*;

/**
 * @author elvis.xu
 * @since 2017-08-24 14:05
 */

@ConstraintComposition(CompositionType.OR)
@Email
@Pattern(regexp = "")
@ReportAsSingleViolation
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface EmailOrMobile {
	String message() default "手机或邮箱";

	@OverridesAttribute(constraint = Pattern.class, name = "regexp") String regexp() default "^\\d{11}$";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
