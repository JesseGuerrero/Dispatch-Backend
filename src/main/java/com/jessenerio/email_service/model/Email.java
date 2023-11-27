package com.jessenerio.email_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    String subject;
    String body;

    public void sendEmail(String toAddress) {
        System.out.println("Sending email to " + toAddress + " with subject " + subject + " and body " + body);
    }
}
