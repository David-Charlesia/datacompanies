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
        connString = getConnectionString();
        settings = MongoClientSettings.builder().applyConnectionString(connString).retryWrites(true).build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(databaseName);
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
