package com.jessenerio.email_service.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class NewsletterAPI {
    @PostMapping("/")
    @ResponseBody
    public String error(HttpServletRequest request) {
        System.out.println("Unsupported request: " + request.getRequestURI());
        return "unsupported";
    }
    @PostMapping("/broadcast")
    @ResponseBody
    public String broadcast(
            @RequestParam String tags,
            @RequestParam String subject,
            @RequestParam String message
    ) {
        System.out.println(tags + " " + subject + " " + message);
        return "success";
    }

    @PostMapping("/add-tag")
    @ResponseBody
    public String addTag(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return "success";
    }

    @PostMapping("/delete-tags")
    @ResponseBody
    public String deleteTags(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return "success";
    }

    @PostMapping("/add-courses")
    @ResponseBody
    public String addCourses(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return "success";
    }

    @PostMapping("/schedule")
    @ResponseBody
    public String schedule(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));;
        return "success";
    }

    @PostMapping("/add-subscriber")
    @ResponseBody
    public String addSubscriber(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return "success";
    }

    @PostMapping("/save-email")
    @ResponseBody
    public String write(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return "success";
    }
}
