package org.lpld.datacompanies.backend;

import java.util.Iterator;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.lpld.datacompanies.backend.mongodb.DatabaseMongoDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Requests {
    private static final String MORE_THAN = "more_than";
    private static final String LESS_THAN = "less_than";
    private static final String EQUALS_TO = "equals_to";
    private static final Logger LOGGER = LoggerFactory.getLogger(Requests.class);
    private DatabaseMongoDB db;
    private BasicDBList query;
    private JSONObject temp;

    public Requests(String cluster_adress){
        this.db = new DatabaseMongoDB(cluster_adress);
    }

    public MongoCursor<Document> doRequest(JSONObject requestFile){
        this.query = new BasicDBList();
        if(requestFile.containsKey(MORE_THAN)){
            this.temp = (JSONObject) requestFile.get(MORE_THAN);
            this.putOptions(MORE_THAN);
        }
        if (requestFile.containsKey(LESS_THAN)) {
            this.temp = (JSONObject) requestFile.get(LESS_THAN);
            this.putOptions(LESS_THAN);
        }
        if (requestFile.containsKey(EQUALS_TO)) {
            this.temp = (JSONObject) requestFile.get(EQUALS_TO);
            this.putOptions(EQUALS_TO);
        }
        BasicDBObject final_query = new BasicDBObject("$and",query);
        LOGGER.info("searching for "+final_query.toString());
        return db.getCollection().find(final_query).cursor();
    }

    private void putOptions(String option){
        Iterator<String> keys = this.temp.keySet().iterator();
        String key;
        Object value;

        if(option.equals(MORE_THAN)){
            while(keys.hasNext()){
                key = keys.next();
                value = temp.get(key);
                this.greaterThan(key, value);
            }
        }else if (option.equals(LESS_THAN)) {
            while (keys.hasNext()) {
                key = keys.next();
                value = temp.get(key);
                this.lessThan(key, value);
            }
        }else if (option.equals(EQUALS_TO)) {
            while (keys.hasNext()) {
                key = keys.next();
                value = temp.get(key);
                this.equalsTo(key, value);
            }
        }
    }

    private void greaterThan(String key, Object value){
        BasicDBObject opt = new BasicDBObject("$gt", value);
        this.query.add(new BasicDBObject(key,opt));
    }
    
    private void lessThan(String key, Object value) {
        BasicDBObject opt = new BasicDBObject("$lt", value);
        this.query.add(new BasicDBObject(key, opt));
    }
    
    private void equalsTo(String key, Object value) {
        BasicDBObject opt = new BasicDBObject(key, value);
        this.query.add(opt);
    }
}
