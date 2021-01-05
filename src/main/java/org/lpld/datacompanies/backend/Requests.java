package org.lpld.datacompanies.backend;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import java.io.FileReader;

import org.bson.BSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Requests {
    public Requests() {}

    public DBCursor doRequest(String jsonFile){
        JSONObject request = parseJson(jsonFile);


        
        DBCursor cursor=null;
        
        return cursor;
    }

    private JSONObject parseJson(String jsonFile){
        try{
            return (JSONObject) new JSONParser().parse(new FileReader(jsonFile));
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    private BasicDBObject lessThan(int quote) {
        return new BasicDBObject("$lt", quote);
    }

    private BasicDBObject greaterThan(int quote){
        return new BasicDBObject("$gt", quote);
    }

    private BasicDBObject lessAndGreaterThan(int lessQuote, int greaterQuote){
        return new BasicDBObject("$lt", lessQuote).append("$gt", greaterQuote);
    }
}
