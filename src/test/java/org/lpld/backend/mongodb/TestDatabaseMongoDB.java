package org.lpld.backend.mongodb;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;
import org.lpld.datacompanies.backend.mongodb.DatabaseMongoDB;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor; 
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.FindIterable;
import java.util.ArrayList;
import java.util.List;

public class TestDatabaseMongoDB {
    @Test
    public void TestDatabaseMongoDB_constructor(){
        DatabaseMongoDB db = new DatabaseMongoDB();
        MongoDatabase database = db.getDataBase();
        MongoCollection<Document> collection = database.getCollection("table");
        //int count = collection.count();
        FindIterable<Document> myDoc = database.getCollection("table").find();
        /*Document doc = new Document("name", "MongoDB").append("type", "database").append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
        collection.insertOne(doc);*/
        System.out.println(myDoc.first());
        Assert.assertNotNull(database);
    }
 
    @Test
    public void TestDocument(){
        DatabaseMongoDB db = new DatabaseMongoDB();
        db.setCollection("Gang");
        Assert.assertNotNull(db.getCollection());
    }

    /*
     db.createUser({ 
      user:'java',
      pwd:'pwd_java',
      roles: [ {role:'readWrite', db:'table' } ]
     });
     */

    

}
