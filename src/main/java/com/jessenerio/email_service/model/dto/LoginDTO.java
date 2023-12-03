package com.jessenerio.email_service.model.dto;

import lombok.Getter;
import lombok.Setter;

import static com.jessenerio.email_service.util.Utils.toTitleCase;

@Getter
@Setter
public class LoginDTO {
    private String username;
    private String password;

    public String getUsername() {
        return toTitleCase(username);
    }
}
