package com.xjd.test.rabbitmq.pubsub;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

/**
 * @author elvis.xu
 * @since 2018-01-12 10:01
 */
public class Subscriber {
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("reputation1");
		factory.setVirtualHost("/test");
		factory.setUsername("test");
		factory.setPassword("test");

		try (
				Connection connection = factory.newConnection();
				Channel channel = connection.createChannel();
		) {
			channel.exchangeDeclare("pubsub", "fanout");
			String queue = channel.queueDeclare().getQueue();
			channel.queueBind(queue, "pubsub", "");

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
										   byte[] body) throws IOException {
					String txt = new String(body, "utf8");
					System.out.println(txt);
				}
			};

			channel.basicConsume(queue, true, consumer);
			Thread.sleep(60000L);
		}
	}
}
