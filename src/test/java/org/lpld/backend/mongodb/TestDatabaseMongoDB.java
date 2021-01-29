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
    public void TestDatabaseMongoDB_constructor(String dns){
        DatabaseMongoDB db = new DatabaseMongoDB(dns);//Ok
        MongoDatabase database = db.getDataBase();
        MongoCollection<Document> collection = db.getCollection();
        FindIterable<Document> myList = collection.find(new Document("siren","005880596"));
        //LOGGER.info(myDoc.first().toString());
        Document doc = myList.first();
        doc.keySet();
        Assert.assertNotNull(database);
        Assert.assertEquals("GEDIMO HOLDING",myList.first().get("denomination"));
        LOGGER.info("c'est ok bro tema : "+doc.toString());
    }
}
