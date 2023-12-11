package com.jessenerio.email_service.model.dto.newsletter;

import lombok.Getter;

@Getter
public class BroadcastToTag {
    private String tags;
    private String subject;
    private String body;

    public String[] getTags() {
        tags = tags.replace(" ", "").toLowerCase();
        return tags.split(",");
    }
}
