package com.jessenerio.email_service.model.dto.authDTOs;

import lombok.Getter;
import lombok.Setter;

import static com.jessenerio.email_service.util.Utils.decodeBase64;
import static com.jessenerio.email_service.util.Utils.toTitleCase;

@Getter
public class ForgotPasswordDTO {
    private String title;

    public String getTitle() {
        return toTitleCase(title);
    }

}
