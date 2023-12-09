package com.jessenerio.email_service.model.dto.newsletterDTOs;

import lombok.Getter;

@Getter
public class DeleteTagDTO {
    private String tags;

    public String[] getTags() {
        tags = tags.replace(" ", "").toLowerCase();
        return tags.split(",");
    }
}
