package com.jessenerio.email_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers") // Use @Document annotation for MongoDB collection
public class Customer {

    @Id // Use @Id annotation for MongoDB document identifier
    private String id;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Boolean isPremium;

    private List<EmailContact> emailList;
    private List<Tag> tags;
    private List<ScheduledEmails> scheduledEmails;
    private List<Course> courses;
    private List<WrittenEmail> emailTemplates;

    public Customer(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isPremium = true;

        this.emailList = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.scheduledEmails = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.emailTemplates = new ArrayList<>();
    }
}