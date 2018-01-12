package com.xjd.test.rabbitmq.tx;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author elvis.xu
 * @since 2018-01-12 18:30
 */
public class ConfirmSender {
	public static void main(String[] args) throws IOException, TimeoutException, TimeoutException,
			InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("reputation1");
		factory.setVirtualHost("/test");
		factory.setUsername("test");
		factory.setPassword("test");


		try (
				Connection connection = factory.newConnection();
				Channel channel = connection.createChannel();
		) {
			channel.addReturnListener(returnMessage -> {
				System.out.println(returnMessage.getReplyText());
			});

			channel.confirmSelect();

			channel.basicPublish("", "NONEXISTS", true, null, "HELLO CONFIRM".getBytes(Charset.forName("utf8")));

			boolean con = channel.waitForConfirms(10L);
			System.out.println(con);
//			channel.waitForConfirmsOrDie(10L);

			Thread.sleep(1000L);
		}
	}
}
