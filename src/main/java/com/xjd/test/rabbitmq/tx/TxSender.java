package com.xjd.test.rabbitmq.tx;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author elvis.xu
 * @since 2018-01-12 17:27
 */
public class TxSender {
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

			channel.txSelect();

			try {
				channel.basicPublish("", "hello", null, "HELLO TX".getBytes(Charset.forName("utf8")));
				// other tasks
				channel.txCommit();
			} catch (Exception e) {
				channel.txRollback();
			}
		}
	}
}
