package com.jessenerio.email_service.model.dto.newsletter;

import com.jessenerio.email_service.util.Utils;
import lombok.Getter;

@Getter
public class RenameNewsletterTitle {
    private String title;

    public String getTitle() {
        return Utils.toTitleCase(title);
    }
}
