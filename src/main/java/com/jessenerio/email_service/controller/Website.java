package com.jessenerio.email_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Website {

    @GetMapping("/signup")
    public String error(Model model) {
        return "fragments/login";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "fragments/index";
    }

    @GetMapping("/broadcast")
    public String broadcast(Model model) {
        return "fragments/broadcast";
    }


    @GetMapping("/manage")
    public String manage(Model model) {
        return "fragments/manage";
    }

    @GetMapping("/scheduler")
    public String schedule(Model model) {
        return "fragments/scheduler";
    }

    @GetMapping("/test")
    public String test(Model model) {
        return "fragments/test";
    }

    @GetMapping("/write")
    public String write(Model model) {
        return "fragments/write";
    }
}