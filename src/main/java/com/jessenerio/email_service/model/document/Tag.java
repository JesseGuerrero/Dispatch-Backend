package com.jessenerio.email_service.model.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private List<String> emails = new ArrayList<>();

    public void addEmail(String email) {
        emails.add(email);
    }
}
