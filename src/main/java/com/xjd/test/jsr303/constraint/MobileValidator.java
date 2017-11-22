package com.xjd.test.jsr303.constraint;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author elvis.xu
 * @since 2017-08-24 14:18
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

	public static Pattern pattern = Pattern.compile("\\d{11}", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return pattern.matcher(value).matches();
	}

	@Override
	public void initialize(Mobile constraintAnnotation) {

	}
}
