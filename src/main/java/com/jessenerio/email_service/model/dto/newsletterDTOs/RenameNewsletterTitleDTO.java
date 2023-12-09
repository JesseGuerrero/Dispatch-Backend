package com.jessenerio.email_service.model.dto.newsletterDTOs;

import com.jessenerio.email_service.util.Utils;
import lombok.Getter;

@Getter
public class RenameNewsletterTitleDTO {
    private String title;

    public String getTitle() {
        return Utils.toTitleCase(title);
    }
}
