package com.jessenerio.email_service.model.dto.newsletter;

import com.jessenerio.email_service.util.Utils;
import lombok.Getter;

@Getter
public class AddEmailToTag {
    private String email;
    private String tags;

    public String getEmail() {
        if(Utils.isValidEmail(email))
            return email.toLowerCase();
        return "";
    }

    public String[] getTags() {
        tags = tags.replace(" ", "").toLowerCase();
        return tags.split(",");
    }
}
