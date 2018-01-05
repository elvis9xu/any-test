package com.xjd.test.mongo.mongo3410;

import java.util.Arrays;
import java.util.List;

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
public class MongoTest2 {
	public static void main(String[] args) {
		MongoCredential credential = MongoCredential.createCredential("reputation_test", "reputation_test", "reputation_test_2017".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("reputation1", 9017), Arrays.asList(credential));
		MongoDatabase db = mongoClient.getDatabase("reputation_test");

		MongoCollection<Document> test = db.getCollection("article");


		String map = "function () {\n" + "\tif (!this.publish_time) return;\n" + "\td = new Date(this.publish_time);\n" + "\tm = d.getMonth() + 1\n" + "\tkey = d.getFullYear() + '' + (m < 10 ? \"0\" + m : m);\n" + "\tvalue = {\"sentiment\": this.sentiment, \"related_companies\": this.related_companies, \"classify\": this.classify}\n" + "    emit(key, value);\n" + "}";
		String reduce = "function (key, values) {\n" + "  map={}\n" + "  for (i in values) {\n" + "    obj = values[i];\n" + "    for (t in obj.related_companies) {\n" + "        c = obj.related_companies[t]\n" + "        ref=map[c];\n" + "        if (!ref) {\n" + "        ref={company_id: c, article_total: 0, sentiment_pos: 0, sentiment_neg: 0, sentiment_neu: 0, classify:{}};\n" + "        map[c]=ref;\n" + "        }\n" + "      ref.article_total += 1;\n" + "      if (obj.sentiment) {\n" + "        if (obj.sentiment == 'pos') ref.sentiment_pos += 1;\n" + "        else if (obj.sentiment == 'neg') ref.sentiment_neg += 1;\n" + "        else if (obj.sentiment == 'neu') ref.sentiment_neu += 1;\n" + "      }\n" + "      if (obj.classify) {\n" + "        for (x in obj.classify) {\n" + "          cf = obj.classify[x]\n" + "          ref.classify[cf] = (ref.classify[cf] ? ref.classify[cf] : 0) + 1\n" + "        }\n" + "      }\n" + "    }\n" + "  }\n" + "\n" + "  data = []\n" + "  for (i in map) {\n" + "    obj = map[i];\n" + "    data.push(obj);\n" + "    cf = [];\n" + "    for (t in obj.classify) {\n" + "      cf.push({name: t, count: obj.classify[t]})\n" + "    }\n" + "    obj.classify = cf;\n" + "  }\n" + "\n" + "  return {data: data}\n" + "}";


		MapReduceIterable<Document> maps = test.mapReduce(map, reduce);

		maps.iterator().forEachRemaining(e -> {
			System.out.println("========");
			System.out.println(e);
			System.out.println("========");
			List<Document> d = e.get("value", Document.class).get("data", List.class);
			System.out.println(d);
			System.out.println("========");
//			System.out.println(d.toJson());
//			System.out.println("========");
			System.out.println(d.getClass());
		});
	}
}
