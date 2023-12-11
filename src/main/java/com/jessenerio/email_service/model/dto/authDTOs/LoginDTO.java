package com.jessenerio.email_service.model.dto.authDTOs;

import lombok.Getter;

import static com.jessenerio.email_service.util.Utils.decodeBase64;
import static com.jessenerio.email_service.util.Utils.toTitleCase;

@Getter
public class LoginDTO {
    private String title;
    private String password;

    public String getTitle() {
        return toTitleCase(title);
    }

    public String getPassword() {
        return decodeBase64(password);
    }
}
