package com.jessenerio.email_service.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.jessenerio.email_service.util.Utils.decodeBase64;
import static com.jessenerio.email_service.util.Utils.toTitleCase;

@Getter
@Setter
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
