package com.jessenerio.email_service.model.repository;

import com.jessenerio.email_service.model.document.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public String createUser(User user) {
        mongoTemplate.save(user, "users");
        return user.getId();
    }

    public User getUserById(String id) {
        return mongoTemplate.findById(id, User.class, "users");
    }

    public Optional<User> findByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return Optional.of(mongoTemplate.findOne(query, User.class, "users"));
    }

    public boolean existsByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.exists(query, User.class, "users");
    }

    public boolean existsByEmail(String email) {
        Query query = new Query(Criteria.where("username").is(email));
        return mongoTemplate.exists(query, User.class, "users");
    }

    public User updateUser(String id, User user) {
        User existingUser = getUserById(id);

        if (existingUser != null) {
            // Update the existing user with the new values
            existingUser.setEmailList(user.getEmailList());
            existingUser.setTags(user.getTags());
            existingUser.setScheduledEmails(user.getScheduledEmails());
            existingUser.setCourses(user.getCourses());
            existingUser.setEmailTemplates(user.getEmailTemplates());
            existingUser.setPassword(user.getPassword());

            // Save the updated user
            mongoTemplate.save(existingUser, "users");
        }

        return existingUser;
    }

    public String deleteUser(String id) {
        User existingUser = getUserById(id);

        if (existingUser != null) {
            // Delete the user
            mongoTemplate.remove(existingUser, "users");
            return id + " has been deleted!";
        } else {
            return "User not found!";
        }
    }
}