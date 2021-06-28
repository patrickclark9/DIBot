package com.telebot;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
public class MongoDBClient {
    MongoClient mongoClient = MongoClients.create("mongodb+srv://mrgrdn:teletubbies@cluster0.wptqr.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
    MongoDatabase database = mongoClient.getDatabase("Unibabottone");// take collection
    public MongoCollection<Document> getCollection(String coll)
    {
        MongoDatabase database = mongoClient.getDatabase("Unibabottone");
        return database.getCollection(coll);
    }

}