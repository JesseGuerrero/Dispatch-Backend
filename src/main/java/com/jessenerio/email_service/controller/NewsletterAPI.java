package com.jessenerio.email_service.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static com.jessenerio.email_service.util.Utils.decodeBase64;

@Controller
@RequestMapping("/api")
public class NewsletterAPI {
    @PostMapping("/")
    public ResponseEntity error(HttpServletRequest request) {
        System.out.println("Unsupported request: " + request.getRequestURI());
        return ResponseEntity.ok("Success");
    }
    @PostMapping("/broadcast")
    public ResponseEntity broadcast(
            @RequestParam String tags,
            @RequestParam String subject,
            @RequestParam String message
    ) {
        System.out.println(tags + " " + subject + " " + message);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/add-tag")
    public ResponseEntity addTag(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/delete-tags")
    public ResponseEntity deleteTags(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/add-courses")
    public ResponseEntity addCourses(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/schedule")
    public ResponseEntity schedule(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));;
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/add-subscriber")
    public ResponseEntity addSubscriber(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/save-email")
    public ResponseEntity write(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/rename-title")
    public ResponseEntity renameTitle(@RequestParam String title) {
        System.out.println(title);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/rename-owner")
    public ResponseEntity renameOwner(@RequestParam String owner) {
        System.out.println(owner);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/rename-email")
    public ResponseEntity renameEmail(@RequestParam String email) {
        System.out.println(email);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/rename-password")
    public ResponseEntity renamePassword(@RequestParam String password) {
        System.out.println(decodeBase64(password));
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/delete-newsletter")
    public ResponseEntity deleteNewsletter() {
        return ResponseEntity.ok("Success");
    }
}
