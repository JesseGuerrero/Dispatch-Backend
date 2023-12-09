package com.jessenerio.email_service.model.dto.newsletterDTOs;

import com.jessenerio.email_service.util.Utils;
import lombok.Getter;

@Getter
public class DeleteEmailDTO {
    private String email;

    public String getEmail() {
        if(Utils.isValidEmail(email))
            return email.toLowerCase();
        return "";
    }
}
