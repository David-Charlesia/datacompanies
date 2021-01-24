package org.lpld.datacompanies.backend;

import java.util.Iterator;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;

import java.io.FileReader;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.lpld.datacompanies.backend.mongodb.DatabaseMongoDB;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Requests {
    private BasicDBObject doc;
    private JSONObject tempJSONFile;
    private DatabaseMongoDB db;
    private static final Logger LOGGER = LoggerFactory.getLogger(Requests.class);
    private static final String MORE_THAN = "more_than";
    private static final String LESS_THAN = "less_than";
    private static final String EQUALS_TO = "equals_to";

    public Requests() {
        this.db = new DatabaseMongoDB();
    }

    public MongoCursor<Document> doRequest(String jsonFile){
        JSONObject requestFile = parseJson(jsonFile);
        this.doc = new BasicDBObject();
        if(requestFile == null){
            LOGGER.warn("JSON file couldn't be parsed");
            return null;
        }

        if(requestFile.containsKey(MORE_THAN)){
            this.tempJSONFile = ((JSONObject) requestFile.get(MORE_THAN));
            this.putOptions(MORE_THAN);
        }
        if (requestFile.containsKey(LESS_THAN)) {
            this.tempJSONFile = ((JSONObject) requestFile.get(LESS_THAN));
            this.putOptions(LESS_THAN);
        }
        if (requestFile.containsKey(EQUALS_TO)) {
            this.tempJSONFile = ((JSONObject) requestFile.get(EQUALS_TO));
            this.putOptions(EQUALS_TO);
        }
        
        return db.getCollection("Decembre2017").find(this.doc).cursor();
    }

    private JSONObject parseJson(String jsonFile){
        try{
            return (JSONObject) new JSONParser().parse(new FileReader(jsonFile));
        }catch(Exception e){
            LOGGER.error(e.toString());
        }
        return null;
    }

    private void putOptions(String option){
        Iterator<?> keys = tempJSONFile.keySet().iterator();
        String key;
        String value;

        try{
            if(option.equals(MORE_THAN)){
                while (keys.hasNext()) {
                    key = (String)keys.next();
                    value = (String)tempJSONFile.get(key);
                    greaterThan(key, Integer.parseInt(value));
                }
            }else if(option.equals(LESS_THAN)){
                while (keys.hasNext()) {
                    key = (String)keys.next();
                    value = (String) tempJSONFile.get(key);
                    lessThan(key, Integer.parseInt(value));
                }
            }else if(option.equals(EQUALS_TO)){
                while (keys.hasNext()) {
                    key = (String)keys.next();
                    value = (String) tempJSONFile.get(key);
                    equalsTo(key, value);
                }
            }
        }catch(NumberFormatException e){
            LOGGER.warn(e.toString());
        }
        
        
    }

    // find greater than e.g. value<quote
    private void lessThan(String key, int quote) {
        this.doc.put(key,new BasicDBObject("$lt", quote));
    }

    //find greater than e.g. quote<value
    private void greaterThan(String key, int quote){
        this.doc.put(key, new BasicDBObject("$gt", quote));
    }

    //find under quotes e.g. lessquote<value<greaterquote
    private void under(String key,int lessQuote, int greaterQuote){
        this.doc.put(key,new BasicDBObject("$lt", lessQuote).append("$gt", greaterQuote));
    }

    private void equalsTo(String key, Object value){
        this.doc.put(key, value);
    }

    public MongoCursor<Document> doRequest(BasicDBObject b){
        return db.getCollection("Decembre2017").find(b).cursor();
    }
}
