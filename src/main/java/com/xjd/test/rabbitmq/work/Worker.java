package com.xjd.test.rabbitmq.work;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

/**
 * @author elvis.xu
 * @since 2018-01-11 10:30
 */
public class Worker {
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("reputation1");
		factory.setVirtualHost("/test");
		factory.setUsername("test");
		factory.setPassword("test");


		List<OneWorker> list = new ArrayList<>(3);
		for (int i = 0; i < 2; i++) {
			OneWorker worker = new OneWorker("W" + i, factory);
			list.add(worker);
		}

		for (OneWorker worker : list) {
			worker.start();
		}
		for (OneWorker worker : list) {
			worker.join();
		}
	}

	public static class OneWorker extends Thread {
		String name;
		ConnectionFactory factory;

		public OneWorker(String name, ConnectionFactory factory) {
			this.name = name;
			this.factory = factory;
		}

		@Override
		public void run() {
			try (Connection connection = factory.newConnection();
				 Channel channel = connection.createChannel();) {

				channel.queueDeclare("hello2", true, false, false, null);

				channel.basicQos(1);

				Consumer consumer = new DefaultConsumer(channel) {
					@Override
					public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
											   byte[] body) throws IOException {
						String s = new String(body, "utf8");
						System.out.println(name + " : " + consumerTag + " > " + new String(body, "utf8"));

						for (int i = 0; i < Integer.parseInt(s.split(":")[1].trim()); i++) {
							try {
								System.out.println(name + " : " + consumerTag + " > " + s + ": " + i);
								Thread.sleep(2000L);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

						if (name.equals("W0")) {
							channel.basicAck(envelope.getDeliveryTag(), false);
						}

					}
				};
				try {
					channel.basicConsume("hello2", false, consumer);
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					Thread.sleep(120000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
