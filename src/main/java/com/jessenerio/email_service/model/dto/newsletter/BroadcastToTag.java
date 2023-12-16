package com.jessenerio.email_service.model.dto.newsletter;

import com.jessenerio.email_service.util.Utils;
import lombok.Getter;

@Getter
public class BroadcastToTag {
    private String fromUsername;
    private String tags;
    private String subject;
    private String body;

    public String getFromUsername() {
        return Utils.toTitleCase(fromUsername);
    }

    public String[] getTags() {
        tags = tags.replace(" ", "").toLowerCase();
        return tags.split(",");
    }
}
