package com.jessenerio.email_service.model.dto.newsletter;

import lombok.Getter;

@Getter
public class DeleteTag {
    private String tags;

    public String[] getTags() {
        tags = tags.replace(" ", "").toLowerCase();
        return tags.split(",");
    }
}
