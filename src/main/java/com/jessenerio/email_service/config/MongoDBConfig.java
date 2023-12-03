//package com.jessenerio.email_service.config;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoDatabase;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
//
//import java.util.ArrayList;
//
//@Configuration
//public class MongoDBConfig {
//
//    @Value("${spring.data.mongodb.host}")
//    private String mongoHost;
//
//    @Value("${spring.data.mongodb.port}")
//    private int mongoPort;
//
//    @Value("${spring.data.mongodb.database}")
//    private String mongoDatabase;
//
//    @Value("${spring.data.mongodb.username}")
//    private String mongoUsername;
//
//    @Value("${spring.data.mongodb.password}")
//    private String mongoPassword;
//
//    @Bean
//    public MongoClient mongoClient() {
//        String connectionString;
//        if (mongoUsername.equals("") || mongoPassword.equals(""))
//            connectionString = String.format("mongodb://%s:%d/%s", mongoHost, mongoPort, mongoDatabase);
//        else
//            connectionString = String.format("mongodb://%s:%s@%s:%d/%s", mongoUsername, mongoPassword, mongoHost, mongoPort, mongoDatabase);
//
//
//        MongoClient mongoClient = MongoClients.create(connectionString);
//
//        // Check and create the database and collection if they don't exist
//        checkAndCreateDatabase(mongoClient);
//
//        return mongoClient;
//    }
//
//    @Bean
//    public MongoDatabase mongoDatabase() {
//        return mongoClient().getDatabase(mongoDatabase);
//    }
//
//    @Bean
//    public SimpleMongoClientDatabaseFactory mongoDbFactory(MongoClient mongoClient) {
//        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoDatabase);
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate(SimpleMongoClientDatabaseFactory mongoDbFactory) {
//        return new MongoTemplate(mongoDbFactory);
//    }
//
//    private void checkAndCreateDatabase(MongoClient mongoClient) {
//        MongoDatabase adminDatabase = mongoClient.getDatabase("admin");
//
//        // Check if the database exists
//        if (!adminDatabase.listCollectionNames().into(new ArrayList<>()).contains(mongoDatabase)) {
//            // Create the database
//            adminDatabase.runCommand(new BasicDBObject("create", mongoDatabase));
//        }
//
//        // Check if the collection exists
//        if (!mongoClient.getDatabase(mongoDatabase).listCollectionNames().into(new ArrayList<>()).contains("customers")) {
//            // Create the collection
//            mongoClient.getDatabase(mongoDatabase).createCollection("customers");
//        }
//    }
//}