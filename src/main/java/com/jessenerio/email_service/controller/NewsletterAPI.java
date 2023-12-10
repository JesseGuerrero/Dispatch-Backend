package com.jessenerio.email_service.controller;


import com.jessenerio.email_service.model.document.Email;
import com.jessenerio.email_service.model.document.Newsletter;
import com.jessenerio.email_service.model.document.Tag;
import com.jessenerio.email_service.model.dto.newsletterDTOs.AddEmailDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.AddEmptyTagDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.BroadcastToTagDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.ChangeNewsletterEmailDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.ChangeNewsletterPasswordDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.DeleteEmailDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.DeleteTagDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.RenameNewsletterOwnerNameDTO;
import com.jessenerio.email_service.model.dto.newsletterDTOs.RenameNewsletterTitleDTO;
import com.jessenerio.email_service.model.service.EMailService;
import com.jessenerio.email_service.model.service.NewsletterService;
import com.jessenerio.email_service.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.jessenerio.email_service.util.Utils.decodeBase64;

@Controller
@RequestMapping("/api")
public class NewsletterAPI {

    private NewsletterService newsletterService;
    private EMailService emailService;

    @Autowired
    public NewsletterAPI(NewsletterService newsletterService, EMailService emailService) {
        this.newsletterService = newsletterService;
        this.emailService = emailService;
    }

    @PostMapping("/")
    public ResponseEntity error(HttpServletRequest request) {
        System.out.println("Unsupported request: " + request.getRequestURI());
        return ResponseEntity.ok("Success");
    }
    @PostMapping("/broadcast")
    public ResponseEntity broadcast(BroadcastToTagDTO broadcastToTagDTO) {
        Newsletter newsletter = (Newsletter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StringBuilder successfulTags = new StringBuilder();
        for (String tagKey : broadcastToTagDTO.getTags()) {
            Tag tag = newsletter.getTags().get(tagKey);
            if (tag == null)
                continue; // Skip if tag doesn't exist
            successfulTags.append(tagKey).append(", ");
            for (String email : tag.getEmails()) {
                Email emailContent = new Email(broadcastToTagDTO.getSubject(), broadcastToTagDTO.getBody());
                emailService.sendEmail(email, emailContent);
            }
        }
        if (successfulTags.toString() == "")
            return ResponseEntity.badRequest().body("No tags found");
        return ResponseEntity.ok("Successfully broadcasted to " + successfulTags);
    }

    @PostMapping("/add-tag")
    public ResponseEntity addTag(AddEmptyTagDTO addEmptyTagDTO) {
        if(addEmptyTagDTO.getTags().length == 0)
            return ResponseEntity.badRequest().body("No tags inserted");
        Newsletter newsletter = (Newsletter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for(String tag : addEmptyTagDTO.getTags())
            newsletter.addTag(tag);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/delete-tags")
    public ResponseEntity deleteTags(DeleteTagDTO deleteTagDTO) {
        if(deleteTagDTO.getTags().length == 0)
            return ResponseEntity.badRequest().body("No tags removed");
        Newsletter newsletter = (Newsletter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for(String tag : deleteTagDTO.getTags())
            newsletter.removeTag(tag);
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
        if(addEmailDTO.getEmail() == "")
            return ResponseEntity.badRequest().body("Email is invalid");
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newsletter.addEmailContact(addEmailDTO.getEmail(), addEmailDTO.getFirstName());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok(addEmailDTO.getEmail() + " added to " + newsletter.getTitle());
    }

    @PostMapping("/remove-subscriber")
    public ResponseEntity addSubscriber(DeleteEmailDTO deleteEmailDTO) {
        if(deleteEmailDTO.getEmail() == "")
            return ResponseEntity.badRequest().body("Email is invalid");
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newsletter.removeEmailContact(deleteEmailDTO.getEmail());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok(deleteEmailDTO.getEmail() + " deleted from " + newsletter.getTitle());
    }

    @PostMapping("/save-email")
    public ResponseEntity write(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/rename-title")
    public ResponseEntity renameTitle(RenameNewsletterTitleDTO renameNewsletterTitleDTO) {
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String originalTitle = newsletter.getTitle();
        newsletter.setTitle(renameNewsletterTitleDTO.getTitle());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok("Renamed title of " + originalTitle + " to " + newsletter.getTitle());
    }

    @PostMapping("/rename-owner")
    public ResponseEntity renameOwner(RenameNewsletterOwnerNameDTO renameNewsletterOwnerNameDTO) {
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String ownerName = newsletter.getOwnerName();
        newsletter.setOwnerName(renameNewsletterOwnerNameDTO.getOwnerName());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok("Renamed owner of " + newsletter.getTitle() + " from " + ownerName + " to " + newsletter.getOwnerName());
    }

    @PostMapping("/rename-email")
    public ResponseEntity renameEmail(ChangeNewsletterEmailDTO changeNewsletterEmailDTO) {
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newsletter.setEmail(changeNewsletterEmailDTO.getEmail());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok("Renamed recovery email of " + newsletter.getTitle() + " to " + Utils.hideEmail(newsletter.getEmail()));
    }

    @PostMapping("/rename-password")
    public ResponseEntity renamePassword(ChangeNewsletterPasswordDTO changeNewsletterPasswordDTO) {
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newsletter.setPassword(changeNewsletterPasswordDTO.getPassword());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok("Successfully updated password");
    }

    @PostMapping("/delete-newsletter")
    public ResponseEntity deleteNewsletter(HttpServletRequest request) {
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newsletterService.deleteNewsletter(newsletter.getId());
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
        return ResponseEntity.ok("Successfully deleted " + newsletter.getTitle());
    }
}
