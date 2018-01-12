package com.xjd.test.rabbitmq.rpc;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

/**
 * @author elvis.xu
 * @since 2018-01-12 10:33
 */
public class SimpleRPCClient {
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
			BlockingQueue responseQueue = new ArrayBlockingQueue(1);

			// 准备
			String replyQueue = channel.queueDeclare().getQueue();
			String correlationId = UUID.randomUUID().toString();
			channel.addReturnListener(returnMessage -> {
				if (correlationId.equals(returnMessage.getProperties().getCorrelationId())) {
					try {
						responseQueue.offer("Exception: " + returnMessage.getReplyText());
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			});

			// 发出消息
			long now = System.currentTimeMillis();
			AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
					.correlationId(correlationId)
					.timestamp(new Date(now))
					.expiration((now + 5000) + "")
					.replyTo(replyQueue)
					.build();
			channel.basicPublish("", "rpc-server", true, props, "HELLO RPC!".getBytes(Charset.forName("utf8")));

			// 接收消息
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
										   byte[] body) throws IOException {
					if (correlationId.equals(properties.getCorrelationId())) {
						responseQueue.offer(new String(body, "utf8"));
					}
				}
			};
			channel.basicConsume(replyQueue, true, consumer);

			Object poll = responseQueue.poll(5, TimeUnit.SECONDS); // 时间要重算哦

			System.out.println("RPC Response [" + (System.currentTimeMillis() - now) + "]: " + poll);
		}
	}
}
