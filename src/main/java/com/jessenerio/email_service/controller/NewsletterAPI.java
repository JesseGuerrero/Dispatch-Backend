package com.jessenerio.email_service.controller;


import com.jessenerio.email_service.model.dto.newsletterDTOs.AddEmailDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.AddEmptyTagDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.BroadcastToTagDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.ChangeNewsletterEmailDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.ChangeNewsletterPasswordDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.DeleteTagDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.RenameNewsletterOwnerNameDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.RenameNewsletterTitleDTO;
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
    public ResponseEntity broadcast(BroadcastToTagDTO broadcastToTagDTO) {
        System.out.println(broadcastToTagDTO);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/add-tag")
    public ResponseEntity addTag(AddEmptyTagDTO addEmptyTagDTO) {
        System.out.println(addEmptyTagDTO);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/delete-tags")
    public ResponseEntity deleteTags(DeleteTagDTO deleteTagDTO) {
        System.out.println(deleteTagDTO);
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
    public ResponseEntity addSubscriber(AddEmailDTO addEmailDTO) {
        System.out.println(addEmailDTO);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/save-email")
    public ResponseEntity write(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/rename-title")
    public ResponseEntity renameTitle(RenameNewsletterTitleDTO renameNewsletterTitleDTO) {
        System.out.println(renameNewsletterTitleDTO);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/rename-owner")
    public ResponseEntity renameOwner(RenameNewsletterOwnerNameDTO renameNewsletterOwnerNameDTO) {
        System.out.println(renameNewsletterOwnerNameDTO);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/rename-email")
    public ResponseEntity renameEmail(ChangeNewsletterEmailDTO changeNewsletterEmailDTO) {
        System.out.println(changeNewsletterEmailDTO
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/rename-password")
    public ResponseEntity renamePassword(ChangeNewsletterPasswordDTO changeNewsletterPasswordDTO) {
        System.out.println(changeNewsletterPasswordDTO);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/delete-newsletter")
    public ResponseEntity deleteNewsletter() {
        return ResponseEntity.ok("Success");
    }
}
