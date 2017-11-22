package com.xjd.test.kafka.demo1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

/**
 * @author elvis.xu
 * @since 2017-10-17 19:19
 */
public class Consumer2 {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "service4:9191");
		props.put("group.id", "test");
		props.put("enable.auto.commit", "false");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		boolean running = true;
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		consumer.subscribe(Arrays.asList("test", "foo"));
		try {
			while(running) {
				ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
				for (TopicPartition partition : records.partitions()) {
					List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
					for (ConsumerRecord<String, String> record : partitionRecords) {
						System.out.println(record);
						// 处理这条消息, 处理可能Collections.singletonMap(partition, new OffsetAndMetadata(record.offset() + 1))重复的消息
						// 提交ID
						consumer.commitSync();
					}
//					long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
//					consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
				}
			}
		} finally {
			consumer.close();
		}
	}
}
