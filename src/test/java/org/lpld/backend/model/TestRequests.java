package org.lpld.backend.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;

import org.junit.Test;
import org.lpld.datacompanies.backend.Requests;
import org.lpld.datacompanies.backend.Requests_old;
import org.lpld.datacompanies.backend.model.AnnualAccount;
import org.bson.BsonDocument;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.lang.ProcessHandle.Info;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestRequests {
    
    private final Requests request = new Requests("ec2-3-138-34-23.us-east-2.compute.amazonaws.com");
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRequests.class);
    /*
    @Test
    public void testDoRequest(){
        JSONObject request_json = new JSONObject();
        JSONObject morethan = new JSONObject();
        morethan.put("DI","2000");
        request_json.put("more_than",morethan);
        MongoCursor<?> cursor = request.doRequest(request_json);
        Assert.assertTrue(cursor.hasNext());
        
        Document doc = (Document)cursor.next();
        Assert.assertNotNull(doc.get("siren"));

        LOGGER.info("document : "+doc.toString());
    }*/
/*
    @Test
    public void testK(){
        BasicDBObject doc = new BasicDBObject();
        doc.put("siren","005880596");
        MongoCursor<Document> cursor = request.doRequest(doc);
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
    }

    //db.getCollection('CollectionName').find({"DepartureDate" : { $gte : new ISODate("2019-06-11T00:00:00.000Z") }})
*/

    @Test
    public void testMorethan(){
        JSONObject req = new JSONObject();
        JSONObject more_than = new JSONObject();

        //JSONObject equals = new JSONObject();
        //equals.put("siren","005880596");
        more_than.put("DI.0", 4500);
        more_than.put("DI.1",21000);

        req.put("more_than",more_than);
        //req.put("equals_to",equals);
        MongoCursor<Document> cursor = request.doRequest(req);
        Document doc;
        if(cursor.hasNext()){
            doc = cursor.next();
            LOGGER.info("Document : "+doc.toString());
            LOGGER.info("siren : "+doc.get("siren").toString());
            LOGGER.info("DI.0:"+doc.get("DI"));
            AnnualAccount ac = new AnnualAccount(doc);
            Assert.assertNotNull(doc);
        }else{
            LOGGER.info("bruh");
            Assert.assertTrue(false);
        }


    }
}
