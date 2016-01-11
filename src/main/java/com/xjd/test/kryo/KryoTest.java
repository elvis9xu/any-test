package com.xjd.test.kryo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * @author elvis.xu
 * @since 2015-11-14 22:24
 */
public class KryoTest {
	public static void main(String[] args) {
		{
			Kryo kryo = new Kryo();

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Output output = new Output(out);

			Person p = new Person();
			p.setName("XXX");
			p.setAge(20);
			kryo.writeObject(output, p);
			output.close();

			byte[] buf = out.toByteArray();
			System.out.println(buf.length);
			System.out.println(new String(buf));

			Input input = new Input(new ByteArrayInputStream(buf));
			p = kryo.readObject(input, Person.class);
			input.close();

			System.out.println(p.toString());
		}
		{
			Kryo kryo = new Kryo();

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Output output = new Output(out);

			Person p = new Person();
			p.setName("XXX");
			p.setAge(20);
			kryo.writeClassAndObject(output, p);
			output.close();

			byte[] buf = out.toByteArray();
			System.out.println(buf.length);
			System.out.println(new String(buf));

			Input input = new Input(new ByteArrayInputStream(buf));
			p = (Person) kryo.readClassAndObject(input);
			input.close();

			System.out.println(p.toString());
		}
		{
			Kryo kryo = new Kryo();
			Registration register = kryo.register(Person.class);
			System.out.println(register.getId());

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Output output = new Output(out);

			Person p = new Person();
			p.setName("XXX");
			p.setAge(20);
			kryo.writeClassAndObject(output, p);
			output.close();

			byte[] buf = out.toByteArray();
			System.out.println(buf.length);
			System.out.println(new String(buf));

			Input input = new Input(new ByteArrayInputStream(buf));
			p = (Person) kryo.readClassAndObject(input);
			input.close();

			System.out.println(p.toString());
		}
		{
			Kryo kryo = new Kryo();
			Registration register = kryo.register(Person.class);
			System.out.println(register.getId());
			register = kryo.register(Person.class);
			System.out.println(register.getId());
		}
		{
			Kryo kryo = new Kryo();
			Registration register = kryo.register(String.class);
			System.out.println(register.getId());

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Output output = new Output(out);

			kryo.writeClassAndObject(output, "H");
			output.close();

			byte[] buf = out.toByteArray();
			System.out.println(buf.length);
			System.out.println(new String(buf));

			Input input = new Input(new ByteArrayInputStream(buf));
			String p = (String) kryo.readClassAndObject(input);
			input.close();

			System.out.println(p.toString());
		}
	}

	public static class Person {
		private String name;
		private int age;

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

		@Override
		public String toString() {
			return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
		}
	}
}
