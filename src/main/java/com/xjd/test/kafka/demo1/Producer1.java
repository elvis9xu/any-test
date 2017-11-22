package com.xjd.test.kafka.demo1;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author elvis.xu
 * @since 2017-10-17 18:50
 */
public class Producer1 {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "service4:9191,server4:9192");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 0);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 5; i++)
//			producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), "B" + Integer.toString(i)));
			producer.send(new ProducerRecord<String, String>("test", 0, "1", "B" + Integer.toString(i)));


		producer.close();
	}
}
