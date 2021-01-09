package org.lpld.datacompanies.backend.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;
import java.util.Arrays;
import java.util.function.Consumer;
import org.bson.Document;

public class DatabaseMongoDB {
    private ConnectionString connString;
    private MongoClientSettings settings;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private String databaseName;
    private MongoCollection<Document> collection;
    private static String pathCredentials = "./src/main/java/org/lpld/datacompanies/backend/mongodb/credentials.json";

    public DatabaseMongoDB() {
        /*
        connString = getConnectionString();
        settings = MongoClientSettings.builder().applyConnectionString(connString).retryWrites(true).build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(databaseName);
        */
        //final String uriString = "mongodb://user1:$admin@ec2-3-135-248-22.us-east-2.compute.amazonaws.com:27017/test?authSource=admin";
        final String uriString = "mongodb://java:pwd_java@54.90.244.234:27017/test";
        // mongodb://[username:password@]host1[:port1][,...hostN[:portN]][/[defaultauthdb][?options]]
        mongoClient = MongoClients.create(uriString);
        database = mongoClient.getDatabase("table");
    }

    private ConnectionString getConnectionString() {
        try{
            JSONObject credentialsJson = (JSONObject) new JSONParser().parse(new FileReader(pathCredentials));
            String username = (String) credentialsJson.get("username");
            String password = (String) credentialsJson.get("password");
            String cluster_address = (String) credentialsJson.get("cluster-address");
            databaseName = (String) credentialsJson.get("database_name");
            return new ConnectionString(
                    "mongodb+srv://" + username + ":" + password + "@" + cluster_address + "/test?w=majority");
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public MongoDatabase getDataBase(){
        return database;
    }

    public void setCollection(String collectionName){
        collection = database.getCollection(collectionName);
    }

    public MongoCollection<Document> getCollection(){
        return this.collection;
    }
}
