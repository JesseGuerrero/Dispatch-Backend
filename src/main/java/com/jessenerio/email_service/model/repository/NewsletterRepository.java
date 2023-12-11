package com.jessenerio.email_service.model.repository;

import com.jessenerio.email_service.model.document.EmailContact;
import com.jessenerio.email_service.model.document.Newsletter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class NewsletterRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public NewsletterRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void setTemporaryPassword(String title, String temporaryPassword) {
        Query query = new Query(Criteria.where("title").is(title));
        Update update = new Update().set("temporaryPassword", temporaryPassword);
        mongoTemplate.updateFirst(query, update, Newsletter.class, "newsletters");
    }

    public String createNewsletter(Newsletter newsletter) {
        mongoTemplate.save(newsletter, "newsletters");
        return newsletter.getId();
    }

    public Newsletter getNewsletterById(String id) {
        return mongoTemplate.findById(id, Newsletter.class, "newsletters");
    }

    public Newsletter findByTitle(String title) {
        Query query = new Query(Criteria.where("title").is(title));
        return mongoTemplate.findOne(query, Newsletter.class, "newsletters");
    }

    public boolean existsByTitle(String title) {
        Query query = new Query(Criteria.where("title").is(title));
        return mongoTemplate.exists(query, Newsletter.class, "newsletters");
    }

    public boolean existsByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.exists(query, Newsletter.class, "newsletters");
    }

    public void updateNewsletter(Newsletter newsletter) {
       mongoTemplate.save(newsletter, "newsletters");
    }

    public String deleteNewsletter(String id) {
        Newsletter existingNewsletter = getNewsletterById(id);

        if (existingNewsletter != null) {
            //TODO email owner email a copy of the backup document and exportable email list for other providers
            // Delete the newsletter
            mongoTemplate.remove(existingNewsletter, "newsletters");
            return id + " has been deleted!";
        } else {
            return "Newsletter not found!";
        }
    }
}