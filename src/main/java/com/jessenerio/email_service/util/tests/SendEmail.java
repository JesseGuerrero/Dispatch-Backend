package com.jessenerio.email_service.util.tests;

import com.jessenerio.email_service.model.service.EMailService;
import com.jessenerio.email_service.model.document.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {
    private EMailService emailService;

    @Autowired
    public SendEmail(EMailService emailService) {
        this.emailService = emailService;
    }

    public void testBroadcast() {
        System.out.println("Sending email...");
        // Example: Sending an email when the application starts
        String toAddress = "jesseguerrero1991@gmail.com";
        Email email = new Email(
                "Test Subject \uD83D\uDE0E",
                "Hello, this is a test email."
        );

        emailService.sendEmailFromAdmin(toAddress, email);

        System.out.println("Email sent successfully!");
    }
}
