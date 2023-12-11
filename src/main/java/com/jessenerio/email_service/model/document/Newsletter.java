package com.jessenerio.email_service.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "newsletters") // Use @Document annotation for MongoDB collection
public class Newsletter implements UserDetails {

    @Id // Use @Id annotation for MongoDB document identifier
    private String id;

    private String title;
    private String ownerName;
    private String email;
    private String password;
    private String temporaryPassword;

    private List<EmailContact> emailList;
    private Map<String, Tag> tags;
    private List<ScheduledEmails> scheduledEmails;
    private List<Course> courses;
    private List<WrittenEmail> emailTemplates;

    public Newsletter(String title, String ownerName, String email, String password) {
        this.title = title;
        this.ownerName = ownerName;
        this.email = email;
        this.password = password;
        this.temporaryPassword = password;

        this.emailList = new ArrayList<>();
        this.tags = new HashMap<>();
        this.scheduledEmails = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.emailTemplates = new ArrayList<>();
    }

    public void addTag(String tagName) {
        tags.put(tagName, new Tag());
    }

    public void removeTag(String tagName) {
        tags.remove(tagName);
    }

    public void addEmailContact(String email, String firstName) {
        emailList.add(new EmailContact(email, firstName));
    }

    public boolean isDuplicateEmail(String email) {
        for (EmailContact emailContact : emailList) {
            if (emailContact.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public void removeEmailContact(String email) {
        emailList.removeIf(emailContact -> emailContact.getEmail().equals(email));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.temporaryPassword = password;
    }



    @Override
    public String getUsername() {
        return this.title;
    }

    // Other UserDetails methods...

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assuming accounts never expire
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assuming accounts are never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Assuming credentials never expire
    }

    @Override
    public boolean isEnabled() {
        return true; // Assuming all accounts are enabled
    }
}
