package com.xjd.test.jafka;

import java.io.IOException;

import com.sohu.jafka.api.FetchRequest;
import com.sohu.jafka.consumer.SimpleConsumer;
import com.sohu.jafka.message.MessageAndOffset;
import com.sohu.jafka.utils.Utils;

public class JafkaHello2 {

	public static void main(String[] args) throws IOException {
		SimpleConsumer consumer = new SimpleConsumer("10.0.0.83", 9092);

		long offset = 0;
		while (true) {
			FetchRequest request = new FetchRequest("demo", 0, offset);
			for (MessageAndOffset msg : consumer.fetch(request)) {
				System.out.println(Utils.toString(msg.message.payload(), "UTF-8"));
				offset = msg.offset;
			}
		}
	}

}
