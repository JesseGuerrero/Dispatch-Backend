package com.jessenerio.email_service.model.dto.newsletterDTOs;

import com.jessenerio.email_service.util.Utils;
import lombok.Getter;

@Getter
public class AddEmailToTagDTO {
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
