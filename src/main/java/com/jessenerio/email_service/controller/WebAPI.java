package com.jessenerio.email_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebAPI {
    @PostMapping("/broadcast")
    public String broadcast(Model model) {
        System.out.println("Broadcasting");
        return "fragments/broadcast";
    }

    @PostMapping("/manage")
    public String manage(Model model) {
        System.out.println("Managing");
        return "fragments/manage";
    }

    @PostMapping("/scheduler")
    public String schedule(Model model) {
        System.out.println("Scheduling");
        return "fragments/scheduler";
    }

    @PostMapping("/test")
    public String test(Model model) {
        System.out.println("Testing");
        return "fragments/test";
    }

    @PostMapping("/write")
    public String write(Model model) {
        System.out.println("Writing");
        return "fragments/write";
    }
}
