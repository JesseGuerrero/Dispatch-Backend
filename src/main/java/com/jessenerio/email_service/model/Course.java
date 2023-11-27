package com.jessenerio.email_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String courseName;
    private List<Email> emails;
    private HashMap<Integer, List<Email>> stages;
}
