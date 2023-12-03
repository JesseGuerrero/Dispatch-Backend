package com.jessenerio.email_service.model.dto;

import lombok.Getter;
import lombok.Setter;

import static com.jessenerio.email_service.util.Utils.toTitleCase;

@Getter
@Setter
public class SignupDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;



    public String getFirstName() {
        return toTitleCase(firstName);
    }

    public String getLastName() {
        return toTitleCase(lastName);
    }

    public String getUsername() {
        return toTitleCase(username);
    }

    public String getEmail() {
        return email.toLowerCase();
    }
}
