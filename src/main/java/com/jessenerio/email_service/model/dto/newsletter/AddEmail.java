package com.jessenerio.email_service.model.dto.newsletter;

import com.jessenerio.email_service.util.Utils;
import lombok.Getter;

@Getter
public class AddEmail {
    private String email;
    private String firstName;
    public String getEmail() {
        if(Utils.isValidEmail(email))
            return email.toLowerCase();
        return "";
    }

    public String getFirstName() {
        return Utils.toTitleCase(firstName);
    }

}
