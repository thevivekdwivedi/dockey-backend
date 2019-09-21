package com.c3.dockey.services;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoService {
    private static MongoService self = null;
    private MongoClientURI uri;
    private MongoClient client;
    private MongoDatabase database;

    private MongoService() {
        uri = uri = new MongoClientURI(
                "mongodb+srv://dsuser:<password>@cluster0-m83c8.mongodb.net/test?retryWrites=true&w=majority");
        client = new MongoClient(uri);
        database = client.getDatabase("test");
    }

    public static MongoService getInstance() {
        if (self == null) {
            self = new MongoService();
        }

        return self;
    }
}

