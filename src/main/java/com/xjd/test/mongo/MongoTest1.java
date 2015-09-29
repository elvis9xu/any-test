package com.xjd.test.mongo;

import java.util.Arrays;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * @author elvis.xu
 * @since 2015-09-04 08:59
 */
public class MongoTest1 {
	public static void main(String[] args) {
		MongoClientURI uri = new MongoClientURI("mongodb://127.0.0.1:27017");
		MongoClient mongoClient = new MongoClient(uri);
		try {
			MongoDatabase db = mongoClient.getDatabase("test");

			MongoCollection collection = db.getCollection("test");

			collection.insertMany(Arrays.asList(
					new Document("name", "MongoDB").append("type", "database").append("count", 1)
							.append("info", new Document("x", 20).append("y", 102)),
					new Document("name", "Mysql").append("type", "rdbms").append("count", 2)
							.append("info", new Document("x", 200).append("y", 300))));

			System.out.println(collection.count());

			Object first = collection.find(new Document("name", "MongoDB")).first();
			System.out.println(first.getClass());
			System.out.println("first = " + first);

			MongoCursor<Document> iterator = collection.find().iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toJson());
			}

			collection.find().forEach(new Block<Document>() {
				@Override
				public void apply(Document o) {
					System.out.println("o = [" + o + "]");
				}
			});

			MongoCursor<Document> iterator1 = collection
					.find(Filters.and(Filters.exists("name"), Filters.regex("type", "db")))
					.sort(Sorts.descending("name")).skip(1).limit(5).iterator();
			while (iterator1.hasNext()) {
				Document next = iterator1.next();
				System.out.println(next);
			}

			System.out.println(collection.find().projection(Projections.excludeId()).first());

			UpdateResult updateResult = collection.updateMany(Filters.eq("name", "MongoDB"), new Document("$set",
					new Document("info.y", 500)));
			System.out.println(updateResult.getModifiedCount());

			DeleteResult deleteResult = collection.deleteOne(Filters.eq("info.y", 500));
			System.out.println(deleteResult.getDeletedCount());

			// BulkWriteResult bulkWriteResult = collection.bulkWrite(Arrays.asList(new InsertOneModel(new
			// Document("_id",
			// 4)), new InsertOneModel(new Document("_id", 5)), new UpdateOneModel(new Document("_id", 4),
			// new Document("$set", new Document("x", 2))), new DeleteOneModel(new Document("_id", 5))),
			// new BulkWriteOptions().ordered(false));

			// System.out.println(bulkWriteResult);

			collection.insertOne(new Document("date", new Date()));

			MongoCursor it = collection.find(Filters.lt("date", new Date())).iterator();
			while (it.hasNext()) {
				Document next = (Document) it.next();
				System.out.println(next);
				System.out.println(next.get("date").getClass());
				System.out.println(((Date) next.get("date")).getTime());
			}

			it = collection.find(Filters.eq("_id", new ObjectId("55ea44943fd8000354c02c91"))).iterator();
			while (it.hasNext()) {
				Document next = (Document) it.next();
				System.out.println(next.toJson());
			}

		} finally {
			mongoClient.close();
		}
	}
}
