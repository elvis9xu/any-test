package com.xjd.test.rabbitmq.hello;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author elvis.xu
 * @since 2018-01-10 15:46
 */
public class Send {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("reputation1");
		factory.setUsername("test");
		factory.setPassword("test");
		factory.setVirtualHost("/test");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		System.out.println(channel.queueDeclare("hello", false, false, false, null));
		channel.basicPublish("", "hello", null, ("Hello World!" + new Random().nextInt()).getBytes(Charset.forName
				("utf8")));
		channel.basicPublish("", "hello", null, ("Hello World!" + new Random().nextInt()).getBytes(Charset.forName
				("utf8")));
		channel.basicPublish("", "hello", null, ("Hello World!" + new Random().nextInt()).getBytes(Charset.forName
				("utf8")));
		System.out.println("send success!");

		channel.close();
		connection.close();
	}
}
