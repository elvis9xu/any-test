package com.xjd.test.mongo;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author elvis.xu
 * @since 2015-09-05 10:58
 */
public class MongoTest2 {
	public static void main(String[] args) {
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		try {
			MongoDatabase db = client.getDatabase("test");
			MongoCollection collection = db.getCollection("test3");

			collection.insertOne(new Document("name", "YYY").append("test2", new DBRef("test2", new ObjectId(
					"55ea64be58a37daafc5ff941"))));

			Document doc = (Document) collection.find(new Document("name", "YYY")).first();
			DBRef ref = (DBRef) doc.get("test2");
			System.out.println(ref);
			System.out.println(doc);
			System.out.println(doc.get("_id"));
			ObjectId objectId = (ObjectId) doc.getObjectId("_id");
			System.out.println(objectId.getTimestamp());
			System.out.println(objectId.getTime());
		} finally {
			client.close();
		}
	}
}
