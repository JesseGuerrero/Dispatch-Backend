package com.jessenerio.email_service.util.tests;

import com.jessenerio.email_service.service.EMailService;
import com.jessenerio.email_service.model.Email;
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
        String toAddress = "jessework@proton.me";
        Email email = new Email(
                "Test Subject",
                "Hello, this is a test email."
        );

        emailService.sendEmail(toAddress, email);

        System.out.println("Email sent successfully!");
    }
}
