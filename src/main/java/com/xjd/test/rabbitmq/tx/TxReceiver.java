package com.xjd.test.rabbitmq.tx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

/**
 * @author elvis.xu
 * @since 2018-01-12 18:25
 */
public class TxReceiver {
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

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
										   byte[] body) throws IOException {
					try {
						System.out.println(new String(body, "utf8"));
						// other tasks
//						int i = 1/ 0;
						channel.basicAck(envelope.getDeliveryTag(), false);
					} catch (Exception e) {
						channel.basicNack(envelope.getDeliveryTag(), false, true);
//						channel.basicReject(envelope.getDeliveryTag(), true);
					}
				}
			};

			channel.basicConsume("hello", false, consumer);
			Thread.sleep(1000L);
		}
	}
}
