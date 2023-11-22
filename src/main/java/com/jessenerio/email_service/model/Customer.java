//package com.jessenerio.email_service.model;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Document(collection = "customers") // Use @Document annotation for MongoDB collection
//public class Customer {
//
//    @Id // Use @Id annotation for MongoDB document identifier
//    private String id;
//
//    private Integer age;
//    private String fullName;
//    private String username;
//
//    @org.springframework.data.mongodb.core.mapping.Field("Email-List") // Use @Field to specify field name in MongoDB
//    private List<Contact> contactList;
//
//    private Boolean isPremium;
//}