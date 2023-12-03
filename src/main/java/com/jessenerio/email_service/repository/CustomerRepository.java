package com.jessenerio.email_service.repository;

import com.jessenerio.email_service.document.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public String createCustomer(Customer customer) {
        mongoTemplate.save(customer, "customers");
        return customer.getId();
    }

    public Customer getCustomerById(String id) {
        return mongoTemplate.findById(id, Customer.class, "customers");
    }

    public Optional<Customer> findByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return Optional.of(mongoTemplate.findOne(query, Customer.class, "customers"));
    }

    public boolean existsByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.exists(query, Customer.class, "customers");
    }

    public boolean existsByEmail(String email) {
        Query query = new Query(Criteria.where("username").is(email));
        return mongoTemplate.exists(query, Customer.class, "customers");
    }

    public Customer updateCustomer(String id, Customer customer) {
        Customer existingCustomer = getCustomerById(id);

        if (existingCustomer != null) {
            // Update the existing customer with the new values
            existingCustomer.setEmailList(customer.getEmailList());
            existingCustomer.setTags(customer.getTags());
            existingCustomer.setScheduledEmails(customer.getScheduledEmails());
            existingCustomer.setCourses(customer.getCourses());
            existingCustomer.setEmailTemplates(customer.getEmailTemplates());

            // Save the updated customer
            mongoTemplate.save(existingCustomer, "customers");
        }

        return existingCustomer;
    }

    public String deleteCustomer(String id) {
        Customer existingCustomer = getCustomerById(id);

        if (existingCustomer != null) {
            // Delete the customer
            mongoTemplate.remove(existingCustomer, "customers");
            return id + " has been deleted!";
        } else {
            return "Customer not found!";
        }
    }
}