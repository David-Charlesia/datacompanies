package org.lpld.datacompanies.backend.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class DatabaseMongoDB {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseMongoDB.class);
    private MongoClient mongoClient;
    private MongoDatabase database;
    private String databaseName;
    private MongoCollection<Document> collection;
    private static String pathCredentials = "./src/main/java/org/lpld/datacompanies/backend/mongodb/credentials.json";

    public DatabaseMongoDB() {
        mongoClient = MongoClients.create(getConnectionString());
        database = mongoClient.getDatabase(databaseName);
    }

    private String getConnectionString() {
        try{
            JSONObject credentialsJson = (JSONObject) new JSONParser().parse(new FileReader(pathCredentials));
            String username = (String) credentialsJson.get("username");
            String password = (String) credentialsJson.get("password");
            String cluster_address = (String) credentialsJson.get("cluster-address");
            databaseName = (String) credentialsJson.get("database_name");
            return ("mongodb://" + username + ":" + password + "@" + cluster_address + ":27017/test");
        }catch(Exception e){
            LOGGER.error(e.toString());
        }
        return null;
    }

    public MongoDatabase getDataBase(){
        return database;
    }

    public MongoCollection<Document> getCollection(String collectionName){
        return this.database.getCollection(collectionName);
    }
}
