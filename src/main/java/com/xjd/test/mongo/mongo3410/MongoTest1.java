package com.xjd.test.mongo.mongo3410;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author elvis.xu
 * @since 2017-11-22 18:52
 */
public class MongoTest1 {
	public static void main(String[] args) {
		MongoCredential credential = MongoCredential.createCredential("reputation_test", "reputation_test", "reputation_test_2017".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("reputation1", 9017), Arrays.asList(credential));
		MongoDatabase db = mongoClient.getDatabase("reputation_test");

		MongoCollection<Document> test = db.getCollection("test");
		System.out.println(test.count());


		MapReduceIterable maps = test.mapReduce("function () {\n" + "    for (v in this.names) {\n" + "      emit(this.names[v], 1)\n" + "    }\n" + "}",
				"function (key, values) {\n" + "    var reducedValue = \"\" + values;\n" + "    return reducedValue;\n" + "}");

		maps.iterator().forEachRemaining(e -> {
			System.out.println(e);
		});
	}
}
