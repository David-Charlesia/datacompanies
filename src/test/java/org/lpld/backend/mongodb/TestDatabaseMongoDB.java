package org.lpld.backend.mongodb;

import com.mongodb.client.MongoDatabase;

import org.junit.Assert;
import org.junit.Test;
import org.lpld.datacompanies.backend.mongodb.DatabaseMongoDB;

public class TestDatabaseMongoDB {
    @Test
    public void TestDatabaseMongoDB_constructor(){
        DatabaseMongoDB db = new DatabaseMongoDB();
        MongoDatabase database = db.getDataBase();
        Assert.assertNotNull(database);
    }

    @Test
    public void TestDocument(){
        DatabaseMongoDB db = new DatabaseMongoDB();
        db.setCollection("Gang");
        Assert.assertNotNull(db.getCollection());
    }
}
