package com.xjd.test.rabbitmq.rpc;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

/**
 * @author elvis.xu
 * @since 2018-01-12 10:33
 */
public class RPCServer {
	public static final String QUEUE = "rpc-server";

	public RPCServer(Channel channel) throws IOException {

		channel.queueDeclare(QUEUE, false, false, true, null);
		channel.basicQos(1);
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[]
					body) throws IOException {
				System.out.println(consumerTag);
				handleRpc(getChannel(), envelope, properties, body);
			}
		};

		channel.basicConsume(QUEUE, false, consumer);
	}

	protected void handleRpc(Channel channel, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
		System.out.println(properties.getCorrelationId());
		System.out.println(properties.getReplyTo());
		System.out.println(properties.getClassName());
		System.out.println(properties.getExpiration());

		AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder().correlationId(properties.getCorrelationId())
				.build();

		try {
			channel.basicPublish("", properties.getReplyTo(), replyProps, body);
			channel.basicAck(envelope.getDeliveryTag(), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
			RPCServer rpcServer = new RPCServer(channel);
			Thread.sleep(120000L);
		}
	}
}
