package com.jessenerio.email_service.model.dto.auth;

import lombok.Getter;

import static com.jessenerio.email_service.util.Utils.toTitleCase;

@Getter
public class ForgotPassword {
    private String title;

    public String getTitle() {
        return toTitleCase(title);
    }

}
