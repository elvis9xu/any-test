package com.xjd.test.jafka;

import java.util.Properties;

import com.sohu.jafka.common.InvalidPartitionException;
import com.sohu.jafka.common.NoBrokersForPartitionException;
import com.sohu.jafka.producer.Producer;
import com.sohu.jafka.producer.ProducerConfig;
import com.sohu.jafka.producer.StringProducerData;
import com.sohu.jafka.producer.serializer.StringEncoder;

public class JafkaHello {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("broker.list", "0:10.0.0.83:9092");
		props.put("serializer.class", StringEncoder.class.getName());
		
		ProducerConfig pconf = new ProducerConfig(props);
		Producer<String, String> p = new Producer<String, String>(pconf);
		
		StringProducerData data = new StringProducerData("demo");
		for (int i = 0; i < 1000; i++) {
			data.add("Hello World #" + i);
		}
		
		try {
			long start = System.currentTimeMillis();
			for (int i = 0; i < 100; i++) {
				p.send(data);
			}
			long cost = System.currentTimeMillis() - start;
			System.out.println("send 100000 message cost: " + cost + "ms, avg: " + (100000 / cost) + "/ms");
		} finally {
			p.close();
		}
	}

}
