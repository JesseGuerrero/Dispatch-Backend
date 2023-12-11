package com.jessenerio.email_service.model.dto.newsletter;

import com.jessenerio.email_service.util.Utils;
import lombok.Getter;

@Getter
public class DeleteEmail {
    private String email;

    public String getEmail() {
        if(Utils.isValidEmail(email))
            return email.toLowerCase();
        return "";
    }
}
