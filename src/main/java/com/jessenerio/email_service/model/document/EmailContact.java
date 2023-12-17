package com.jessenerio.email_service.model.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailContact {
    private String email;
    private String firstName;
    //For Frontend
//    private HashMap<String, Object> statistics;
}
