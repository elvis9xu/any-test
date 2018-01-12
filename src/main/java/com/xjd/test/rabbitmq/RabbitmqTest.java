package com.xjd.test.rabbitmq;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

/**
 * @author elvis.xu
 * @since 2018-01-12 10:35
 */
public class RabbitmqTest {
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
			channel.addReturnListener((returnMessage) -> {
				System.out.println(returnMessage.getReplyText());
				System.out.println(returnMessage.getProperties());
			});
			channel.basicPublish("", "hello", true, null, ("").getBytes(Charset.forName("utf8")));

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
										   byte[] body) throws IOException {
					envelope.isRedeliver();
				}
			};



			Thread.sleep(10000L);

		}


	}
}
