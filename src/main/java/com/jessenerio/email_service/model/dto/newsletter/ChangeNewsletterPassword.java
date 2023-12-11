package com.jessenerio.email_service.model.dto.newsletter;

import lombok.Getter;

import static com.jessenerio.email_service.util.Utils.decodeBase64;

@Getter
public class ChangeNewsletterPassword {
    private String password;

    public String getPassword() {
        return decodeBase64(password);
    }
}
