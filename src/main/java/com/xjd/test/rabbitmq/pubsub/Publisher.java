package com.xjd.test.rabbitmq.pubsub;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author elvis.xu
 * @since 2018-01-11 15:45
 */
public class Publisher {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("reputation1");
		factory.setVirtualHost("/test");
		factory.setUsername("test");
		factory.setPassword("test");

		final String EXCHANGE_NAME = "pubsub";
		try (
				Connection connection = factory.newConnection();
				Channel channel = connection.createChannel();
		) {
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

			for (int i = 0; i < 10; i++) {
				channel.basicPublish(EXCHANGE_NAME, "", null, (i + "").getBytes(Charset.forName("utf8")));
			}
		}


	}
}
