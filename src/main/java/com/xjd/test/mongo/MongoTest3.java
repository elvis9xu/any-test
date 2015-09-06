package com.xjd.test.mongo;

import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * @author elvis.xu
 * @since 2015-09-05 16:05
 */
public class MongoTest3 {
	public static void main(String[] args) {
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

		try {
			Morphia morphia = new Morphia();

		} finally {
			client.close();
		}
	}

	public static class Person {
		private String name;
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
	}
}
