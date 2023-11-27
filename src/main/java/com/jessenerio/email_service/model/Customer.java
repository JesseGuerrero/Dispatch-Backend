package com.jessenerio.email_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers") // Use @Document annotation for MongoDB collection
public class Customer {

    @Id // Use @Id annotation for MongoDB document identifier
    private String id;

    private Integer age;
    private String fullName;
    private String username;
    private String password;
    private Boolean isPremium;

    private List<EmailContact> emailList;
    private List<Tag> tags;
    private List<ScheduledEmails> scheduledEmails;
    private List<Course> courses;
    private List<WrittenEmail> emailTemplates;
}