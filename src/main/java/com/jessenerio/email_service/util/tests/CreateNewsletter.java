package com.jessenerio.email_service.util.tests;

import com.jessenerio.email_service.model.document.Newsletter;
import com.jessenerio.email_service.model.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNewsletter {
    NewsletterService newsletterDetailsService;

    @Autowired
    public CreateNewsletter(NewsletterService newsletterDetailsService) {
        this.newsletterDetailsService = newsletterDetailsService;
    }
    public void testCreateNewsletter() {
        System.out.println("Creating Newsletter...");
        // Example: Creating a newsletter
        String title = "Jesse";
        String fullName = "jessenerio";
        String email = "jesseguerrero1991@gmail.com";
        String password = "password";
        Newsletter newsletter = new Newsletter(
                title,
                fullName,
                email,
                password
        );

        this.newsletterDetailsService.createNewsletter(newsletter);
    }
}
