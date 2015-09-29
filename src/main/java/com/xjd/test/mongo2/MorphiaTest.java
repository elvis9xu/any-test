package com.xjd.test.mongo2;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.xjd.test.mongo2.entity.People;
import com.xjd.test.mongo2.entity.Person;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.Arrays;
import java.util.List;

public class MorphiaTest {

	public static void main(String[] args) {
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

		try {
			Morphia morphia = new Morphia();
			morphia.mapPackage("com.xjd.test.mongo2.entity");

			Datastore testDb = morphia.createDatastore(client, "test");
			testDb.ensureIndexes();

			{
				Person person = new Person();
				person.setName("XJD");
				person.setAge(20);
				testDb.save(person);
			}

			{
				Query<Person> personQuery = testDb.createQuery(Person.class);
				personQuery.filter("age >=", 20);

				List<Person> list = personQuery.asList();
				for (Person person : list) {
					System.out.println(person);
				}
			}

			{
				Query<Person> personQuery = testDb.createQuery(Person.class);
				List<Person> personList = personQuery.field("age").greaterThanOrEq(20).asList();
				for (Person person : personList) {
					System.out.println(person);
				}
			}

			{
				Query<Person> personQuery = testDb.createQuery(Person.class);
				personQuery.or(personQuery.criteria("age").lessThan(30), personQuery.criteria("age").greaterThan(19));

				List<Person> list = personQuery.asList();
				for (Person person : list) {
					System.out.println(person);
				}
				
			}

			{
				People people = new People();
				people.setGroupName("XXX");
				Person person = new Person();
				person.setName("HAHA");
				person.setAge(100);
				people.setPersonList(Arrays.asList(new Person(), person));
				testDb.save(people);
			}
			

		} finally {
			client.close();
		}
	}


}
