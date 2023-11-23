package com.jessenerio.email_service.controller;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Controller
public class Website {
    @GetMapping("/broadcast")
    public String broadcast(Model model) {
        return "fragments/broadcast";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "fragments/index";
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
