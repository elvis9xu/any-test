package com.xjd.test.rabbitmq.work;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author elvis.xu
 * @since 2018-01-11 10:22
 */
public class Boss {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("reputation1");
		factory.setVirtualHost("/test");
		factory.setUsername("test");
		factory.setPassword("test");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare("hello2", true, false, false, null);
		for (int i = 0; i < 10; i++) {
			channel.basicPublish("", "hello2", null, (i + ": " + new Random().nextInt(10)).getBytes(Charset
					.forName("utf8")));
		}

		channel.close();
		connection.close();
	}

}
