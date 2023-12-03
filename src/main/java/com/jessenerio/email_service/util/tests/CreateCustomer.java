package com.jessenerio.email_service.util.tests;

import com.jessenerio.email_service.document.Customer;
import com.jessenerio.email_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomer {
    CustomerService customerService;

    @Autowired
    public CreateCustomer(CustomerService customerService) {
        this.customerService = customerService;
    }
    public void testCreateCustomer() {
        System.out.println("Creating customer...");
        // Example: Creating a customer
        String firstName = "Jesse";
        String lastName = "Nerio";
        String username = "jessenerio";
        String email = "jesseguerrero1991@gmail.com";
        String password = "password";
        Customer customer = new Customer(
                firstName,
                lastName,
                username,
                email,
                password
        );

        this.customerService.createCustomer(customer);
    }
}
