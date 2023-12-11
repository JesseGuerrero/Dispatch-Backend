package com.jessenerio.email_service.model.dto.auth;

import lombok.Getter;

import static com.jessenerio.email_service.util.Utils.decodeBase64;
import static com.jessenerio.email_service.util.Utils.toTitleCase;

@Getter
public class Signup {
    private String title;
    private String ownerName;
    private String email;
    private String password;



    public String getTitle() {
        return toTitleCase(title);
    }

    public String getOwnerName() {
        return toTitleCase(ownerName);
    }

    public String getEmail() {
        return email.toLowerCase();
    }

    public String getPassword() {
        return decodeBase64(password);
    }
}
