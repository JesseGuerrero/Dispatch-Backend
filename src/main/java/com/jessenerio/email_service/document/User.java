package com.jessenerio.email_service.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users") // Use @Document annotation for MongoDB collection
public class User implements UserDetails {

    @Id // Use @Id annotation for MongoDB document identifier
    private String id;

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    private List<EmailContact> emailList;
    private List<Tag> tags;
    private List<ScheduledEmails> scheduledEmails;
    private List<Course> courses;
    private List<WrittenEmail> emailTemplates;

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;

        this.emailList = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.scheduledEmails = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.emailTemplates = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                () -> "ROLE_USER",
                () -> "ROLE_MEMBER",
                () -> "ROLE_ADMIN"
        );
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
