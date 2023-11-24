package com.jessenerio.email_service.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebAPI {
    @PostMapping("/")
    @ResponseBody
    public String error(HttpServletRequest request) {
        System.out.println("Unsupported request: " + request.getRequestURI());
        return "unsupported";
    }
    @PostMapping("/broadcast")
    @ResponseBody
    public String broadcast(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
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
