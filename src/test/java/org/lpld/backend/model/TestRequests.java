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
    
    private final Requests request = new Requests("ec2-3-135-217-232.us-east-2.compute.amazonaws.com");
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRequests.class);

    @Test
    public void testMorethan(){
        JSONObject req = new JSONObject();
        JSONObject more_than = new JSONObject();

        JSONObject equals = new JSONObject();
        //equals.put("siren","005880596");
        more_than.put("CO.0", 4500);
        //more_than.put("DI.1",21000);

        req.put("more_than",more_than);
        Object val = 15;
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
