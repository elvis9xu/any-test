package com.xjd.test.commons4e$bobuilder;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * <pre>
 *
 * 插件commons4e(toString(),hash(),equals(),compareTo()自动生成)和bobbuilder(builder自动生成)的测试
 * </pre>
 * @author  elvis.xu
 * @version	2013-11-4 上午10:56:53
 */
public class JavaBean implements Comparable<JavaBean>, Serializable {
	private static final long serialVersionUID = 1152489894145611510L;

	private String name;
	private int age;

	public JavaBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int compareTo(final JavaBean other) {
		return new CompareToBuilder().append(name, other.name).append(age, other.age).toComparison();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof JavaBean))
			return false;
		JavaBean castOther = (JavaBean) other;
		return new EqualsBuilder().append(name, castOther.name).append(age, castOther.age).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(age).toHashCode();
	}

	private JavaBean(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
	}

	public static class Builder {

		private String name;
		private int age;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withAge(int age) {
			this.age = age;
			return this;
		}

		public JavaBean build() {
			validate();
			return new JavaBean(this);
		}

		private void validate() {
			Validate.isTrue(!StringUtils.isBlank(name), "name may not be blank");
			Validate.isTrue(age > 0, "age should be set");
		}
	}

	public static void main(String[] args) {
		JavaBean b1 = new JavaBean.Builder().withName("HELLO").withAge(10).build();
		System.out.println(b1.toString() + "----" + b1.hashCode());

		JavaBean b2 = new JavaBean.Builder().withName("HELLO").withAge(10).build();
		System.out.println(b1.compareTo(b2) + "---------" + b1.equals(b2));
	}
}
