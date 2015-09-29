package com.xjd.test.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class GoogleGson {

	public static void main(String[] args) {
		Gson gson = new GsonBuilder().create();

		Person p = new Person();
		p.setName("XJD");
		p.setAge(20);

		String str = gson.toJson(p);
		System.out.println(str);
		System.out.println(gson.toJson(str));

		String s = "{\"name\":\"XJD\",\"age-1\":20}";
		Person tp = gson.fromJson(s, Person.class);
		System.out.println(tp);
	}

	public static class Person {
		private String name;
		@SerializedName("age-1")
		private Integer age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + "]";
		}

	}
}
