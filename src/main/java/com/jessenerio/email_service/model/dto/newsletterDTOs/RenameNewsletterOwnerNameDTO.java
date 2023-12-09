package com.jessenerio.email_service.model.dto.newsletterDTOs;

import com.jessenerio.email_service.util.Utils;
import lombok.Getter;

@Getter
public class RenameNewsletterOwnerNameDTO {
    private String ownerName;

    public String getOwnerName() {
        return Utils.toTitleCase(ownerName);
    }
}
