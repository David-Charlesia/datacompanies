package org.lpld.datacompanies.backend.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class DatabaseMongoDB {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseMongoDB.class);
    private MongoClient mongoClient;
    private MongoDatabase database;
    private String databaseName;
    private String collectionName;
    private MongoCollection<Document> collection;
    //private static String pathCredentials = "https://sxhdeg.db.files.1drv.com/y4md-qbjhIr94fvoZLJS-fKN-WF50cqn6c8HEUIEyT-TEYBcyUYBqbLQBh10f4GiBtpIVDTVQKad76gypXLgxWrWgwOd9vLSxUt1bG7iqYUGOdxMAGv8at7538F4p8Stc40MEo2ERV-RjlNkLUq24lQgKyXC3IYaHdsl8_e3OfXyVhPxhaY1Jw9kBWdH3fZpWSJ-Etc6Q8Z5wWWrwfXfFFnRw";

    public DatabaseMongoDB(String dns) {
        this.mongoClient = MongoClients.create(getConnectionString(dns));
        this.database = mongoClient.getDatabase(this.databaseName);
        this.collection = this.database.getCollection(this.collectionName);
    }

    private String getConnectionString(String dns) {
        String username = "datacompanies_program";
        String password = "F4f295rT2Hs9b2377paU4W8w4JY4GYTh63mcqzZZXKjZCu4PD3s852L8ScacBG5BmT82cgrkr";
        //String clusterAddress = "ec2-18-191-14-80.us-east-2.compute.amazonaws.com";
        this.databaseName = "Entreprise";
        this.collectionName = "Comptes_annuels";

        return ("mongodb://" + username + ":" + password + "@" + dns + ":27017/test");
    }

    public MongoDatabase getDataBase(){
        return this.database;
    }

    public MongoCollection<Document> getCollection(String collectionName){
        return this.database.getCollection(collectionName);
    }

    public MongoCollection<Document> getCollection(){
        return this.collection;
    }

    public int OnePlusOne(){
        return 1+1;
    }
}
