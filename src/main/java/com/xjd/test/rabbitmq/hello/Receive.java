package com.xjd.test.rabbitmq.hello;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

/**
 * @author elvis.xu
 * @since 2018-01-10 16:23
 */
public class Receive {
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("reputation1");
		factory.setVirtualHost("/test");
		factory.setUsername("test");
		factory.setPassword("test");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare("hello", false, false, false, null);

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[]
					body) throws IOException {
				System.out.println(consumerTag);
				System.out.println(new String(body, "utf8"));
			}
		};

		channel.basicConsume("hello", true, consumer);

		Thread.sleep(2000L);

		channel.close();
		connection.close();
	}
}
