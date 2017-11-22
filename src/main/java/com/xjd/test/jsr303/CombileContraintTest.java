package com.xjd.test.jsr303;

import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import lombok.Getter;
import lombok.Setter;

import com.xjd.test.jsr303.constraint.Mobile;
import com.xjd.test.jsr303.constraint.MobileValidator;

/**
 * @author elvis.xu
 * @since 2017-08-24 11:52
 */
public class CombileContraintTest {
	static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public static void main(String[] args) {



		Bean bean = new Bean();
		bean.setName("18888888888");

		valid(bean);
		System.out.println("-----------");
		MobileValidator.pattern = Pattern.compile("^\\d{12}$", Pattern.CASE_INSENSITIVE);
		valid(bean);
	}


	public static void valid(Bean bean) {
		Set<ConstraintViolation<Bean>> validate = validator.validate(bean);
		for (ConstraintViolation<Bean> beanConstraintViolation : validate) {
			System.out.println(beanConstraintViolation);
		}
	}

	@Getter
	@Setter
	public static class Bean {
//		@EmailOrMobile
		@Mobile
		private String name;
	}
}
