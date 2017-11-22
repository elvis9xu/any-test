package com.xjd.test.jsr303;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * @author elvis.xu
 * @since 2017-08-18 15:49
 */
public class GroupTest {
	public static void main(String[] args) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


		Set<ConstraintViolation<Person>> age = validator.validateValue(Person.class, "age", 20, Group.A.class);
		print(age);

		age = validator.validateValue(Person.class, "age", 17, Group.B.class);
		print(age);
	}

	public static void print(Set<ConstraintViolation<Person>> set) {
		for (ConstraintViolation<Person> p : set) {
			System.out.println(p.getPropertyPath());
			System.out.println(p.getConstraintDescriptor());
			System.out.println(p.getInvalidValue());
			System.out.println(p.getMessage());
			System.out.println(p.getMessageTemplate());
		}
	}


	public static interface Group {

		public static interface A{}
		public static interface B{}
	}


	@Getter
	@Setter
	public static class Person {

		@Pattern(regexp = "^\\D*$", groups = Group.A.class)
		@Pattern(regexp = "^\\d*$", groups = Group.B.class)
		private String name;

		@Max(value = 18, groups = Group.A.class)
		@Min(value = 18, groups = Group.B.class)
		private Integer age;

		private String address;
	}
}
