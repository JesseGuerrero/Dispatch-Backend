package com.jessenerio.email_service.util.tests;

import com.jessenerio.email_service.model.document.User;
import com.jessenerio.email_service.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {
    UserService userDetailsService;

    @Autowired
    public CreateUser(UserService userDetailsService) {
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
