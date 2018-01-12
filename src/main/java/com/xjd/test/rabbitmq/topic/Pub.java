package com.xjd.test.rabbitmq.topic;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author elvis.xu
 * @since 2018-01-12 10:20
 */
public class Pub {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("reputation1");
		factory.setVirtualHost("/test");
		factory.setUsername("test");
		factory.setPassword("test");

		try (
				Connection connection = factory.newConnection();
				Channel channel = connection.createChannel();
		) {
			channel.basicPublish("test.routing", "q.x", null, "q.x".getBytes(Charset.forName("utf8")));
			channel.basicPublish("test.routing", "q1.x", null, "q1.x".getBytes(Charset.forName("utf8")));
			channel.basicPublish("test.routing", "q2.x", null, "q2.x".getBytes(Charset.forName("utf8")));
			channel.basicPublish("test.routing", "q2.x2", null, "q2.x2".getBytes(Charset.forName("utf8")));
		}
	}
}
