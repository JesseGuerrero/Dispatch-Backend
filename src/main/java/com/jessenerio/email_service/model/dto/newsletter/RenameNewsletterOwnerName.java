package com.jessenerio.email_service.model.dto.newsletter;

import com.jessenerio.email_service.util.Utils;
import lombok.Getter;

@Getter
public class RenameNewsletterOwnerName {
    private String ownerName;

    public String getOwnerName() {
        return Utils.toTitleCase(ownerName);
    }
}
