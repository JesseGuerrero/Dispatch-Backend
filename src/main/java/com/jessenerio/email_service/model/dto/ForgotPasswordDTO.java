package com.jessenerio.email_service.model.dto;

import lombok.Getter;
import lombok.Setter;

import static com.jessenerio.email_service.util.Utils.decodeBase64;
import static com.jessenerio.email_service.util.Utils.toTitleCase;

@Getter
@Setter
public class ForgotPasswordDTO {
    private String title;
    private String password;

    public String getTitle() {
        return toTitleCase(title);
    }

}
