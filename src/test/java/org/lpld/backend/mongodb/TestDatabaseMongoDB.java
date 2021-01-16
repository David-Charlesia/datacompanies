package org.lpld.backend.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.lpld.datacompanies.backend.mongodb.DatabaseMongoDB;
import com.mongodb.client.FindIterable;

public class TestDatabaseMongoDB {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDatabaseMongoDB.class);

    @Test
    public void TestDatabaseMongoDB_constructor(){
        DatabaseMongoDB db = new DatabaseMongoDB();
        MongoDatabase database = db.getDataBase();
        MongoCollection<Document> collection = db.getCollection("Decembre2017");
        FindIterable<Document> myDoc = collection.find(new Document("siren","005880596"));
        LOGGER.info(myDoc.first().toString());
        Assert.assertNotNull(database);
        Assert.assertEquals("GEDIMO HOLDING",myDoc.first().get("denomination"));
    }
}
