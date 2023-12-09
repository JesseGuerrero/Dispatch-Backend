package com.jessenerio.email_service.model.dto.newsletterDTOs;

import lombok.Getter;

@Getter
public class AddEmptyTagDTO {
    private String tags;

    public String[] getTags() {
        tags = tags.replace(" ", "").toLowerCase();
        return tags.split(",");
    }
}
