package com.xjd.test.kafka.demo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author elvis.xu
 * @since 2017-10-17 19:11
 */
public class Consumer1 {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "service4:9192");
		props.put("group.id", "test");
		props.put("enable.auto.commit", "false");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("foo", "test"));
		final int minBatchSize = 1;
		List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				buffer.add(record);
			}
			if (buffer.size() >= minBatchSize) {
//				insertIntoDb(buffer);
				for (ConsumerRecord<String, String> record : buffer) {
					System.out.println(record);
				}
				consumer.commitSync();
				buffer.clear();
			}
		}
	}
}
