package com.jessenerio.email_service.util.tests;

import com.jessenerio.email_service.service.EMailService;
import com.jessenerio.email_service.document.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {
    private EMailService emailService;

    @Value("${spring.mail.username}")
    private String fromAddress;

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

        emailService.sendEmail(fromAddress, toAddress, email);

        System.out.println("Email sent successfully!");
    }
}
