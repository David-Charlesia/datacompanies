package org.lpld.backend.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;

import org.junit.Test;
import org.lpld.datacompanies.backend.Requests;
import org.lpld.datacompanies.backend.model.AnnualAccount;
import org.bson.BsonDocument;
import org.bson.Document;
import org.junit.Assert;

import java.lang.ProcessHandle.Info;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestRequests {
    /*
    private final Requests request = new Requests();
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRequests.class);
    
    @Test
    public void testDoRequest(){
        MongoCursor<?> cursor = request.doRequest("./src/main/java/org/lpld/datacompanies/backend/examplerequest.json");
        Assert.assertTrue(cursor.hasNext());
        
        Document doc = (Document)cursor.next();
        Assert.assertNotNull(doc.get("siren"));

        LOGGER.info("document : "+doc.toString());
    }

    @Test
    public void testK(){
        BasicDBObject doc = new BasicDBObject();
        doc.put("siren","005880596");
        MongoCursor<?> cursor = request.doRequest(doc);
        Assert.assertTrue(cursor.hasNext());
    }

    @Test
    public void testDate(){
        BasicDBObject doc = new BasicDBObject();
        String date="";
        try{
            date = new SimpleDateFormat("dd/MM/yyyy").parse("30/12/2016").toString();
        }catch(ParseException e){
            LOGGER.error(e.toString());
        }
        doc.put("date_cloture_exercice", new BasicDBObject("$gte", date));
        MongoCursor<?> cursor = request.doRequest(doc);

        Document docResponse = (Document) cursor.next();
        LOGGER.info("date cloture exercice = "+docResponse.get("date_cloture_exercice").toString());
        Assert.assertNotNull(docResponse.get("siren"));
    }*/

    //db.getCollection('CollectionName').find({"DepartureDate" : { $gte : new ISODate("2019-06-11T00:00:00.000Z") }})
}
