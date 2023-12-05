package com.jessenerio.email_service.model.dto;

import lombok.Getter;
import lombok.Setter;

import static com.jessenerio.email_service.util.Utils.toTitleCase;

@Getter
@Setter
public class SignupDTO {
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
}
