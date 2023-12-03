package com.jessenerio.email_service.util.tests;

import com.jessenerio.email_service.document.User;
import com.jessenerio.email_service.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {
    UserDetailsService userDetailsService;

    @Autowired
    public CreateUser(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    public void testCreateUser() {
        System.out.println("Creating user...");
        // Example: Creating a user
        String firstName = "Jesse";
        String lastName = "Nerio";
        String username = "jessenerio";
        String email = "jesseguerrero1991@gmail.com";
        String password = "password";
        User user = new User(
                firstName,
                lastName,
                username,
                email,
                password
        );

        this.userDetailsService.createUser(user);
    }
}
