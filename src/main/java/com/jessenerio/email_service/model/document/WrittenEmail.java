package com.jessenerio.email_service.model.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WrittenEmail {
    String emailName;
    String subject;
    String body;
}
