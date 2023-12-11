package com.jessenerio.email_service.controller;


import com.jessenerio.email_service.model.document.Email;
import com.jessenerio.email_service.model.document.Newsletter;
import com.jessenerio.email_service.model.document.Tag;
import com.jessenerio.email_service.model.dto.newsletter.AddEmail;
import com.jessenerio.email_service.model.dto.newsletter.AddEmailToTag;
import com.jessenerio.email_service.model.dto.newsletter.AddEmptyTag;
import com.jessenerio.email_service.model.dto.newsletter.BroadcastToTag;
import com.jessenerio.email_service.model.dto.newsletter.ChangeNewsletterEmail;
import com.jessenerio.email_service.model.dto.newsletter.ChangeNewsletterPassword;
import com.jessenerio.email_service.model.dto.newsletter.DeleteEmail;
import com.jessenerio.email_service.model.dto.newsletter.DeleteTag;
import com.jessenerio.email_service.model.dto.newsletter.RenameNewsletterOwnerName;
import com.jessenerio.email_service.model.dto.newsletter.RenameNewsletterTitle;
import com.jessenerio.email_service.model.service.EMailService;
import com.jessenerio.email_service.model.service.NewsletterService;
import com.jessenerio.email_service.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public ResponseEntity broadcast(@RequestBody
                                    BroadcastToTag broadcastToTagDTO) {
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
    public ResponseEntity addTag(@RequestBody AddEmptyTag addEmptyTagDTO) {
        if(addEmptyTagDTO.getTags().length == 0)
            return ResponseEntity.badRequest().body("No tags inserted");
        Newsletter newsletter = (Newsletter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for(String tag : addEmptyTagDTO.getTags())
            newsletter.addTag(tag);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/add-email-to-tag")
    public ResponseEntity addEmailToTag(@RequestBody
                                        AddEmailToTag addEmailToTagDTO) {
        String email = addEmailToTagDTO.getEmail();
        String[] tags = addEmailToTagDTO.getTags();
        if(tags.length == 0)
            return ResponseEntity.badRequest().body("No tags detected from input");
        if(email == "")
            return ResponseEntity.badRequest().body("Email is invalid");
        Newsletter newsletter = (Newsletter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!newsletter.hasEmailInList(email))
            return ResponseEntity.badRequest().body(email + " not in the " + newsletter.getTitle() + " newsletter list");
        StringBuilder successMessage = newsletter.addEmailToTags(email, tags);
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok(successMessage);
    }

    @PostMapping("/delete-tags")
    public ResponseEntity deleteTags(@RequestBody DeleteTag deleteTagDTO) {
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
    public ResponseEntity addSubscriber(@RequestBody AddEmail addEmailDTO) {
        String email = addEmailDTO.getEmail();
        if(email == "")
            return ResponseEntity.badRequest().body("Email is invalid");
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(newsletter.isDuplicateEmail(email))
            return ResponseEntity.badRequest().body("Email already exists");
        newsletter.addEmailContact(email, addEmailDTO.getFirstName());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok(email + " added to " + newsletter.getTitle());
    }

    @PostMapping("/remove-subscriber")
    public ResponseEntity addSubscriber(@RequestBody DeleteEmail deleteEmailDTO) {
        String email = deleteEmailDTO.getEmail();
        if(email == "")
            return ResponseEntity.badRequest().body("Email is invalid");
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!newsletter.hasEmailInList(email))
            return ResponseEntity.badRequest().body(email + " email does not exist in " + newsletter.getTitle() + " newsletter");
        newsletter.removeEmailContact(email);
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok(email + " deleted from " + newsletter.getTitle());
    }

    @PostMapping("/save-email")
    public ResponseEntity write(HttpServletRequest request) {
        for(String key : request.getParameterMap().keySet())
            System.out.println(key + ": " + request.getParameter(key));
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/rename-title")
    public ResponseEntity renameTitle(@RequestBody
                                      RenameNewsletterTitle renameNewsletterTitleDTO) {
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String originalTitle = newsletter.getTitle();
        newsletter.setTitle(renameNewsletterTitleDTO.getTitle());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok("Renamed title of " + originalTitle + " to " + newsletter.getTitle());
    }

    @PostMapping("/rename-owner")
    public ResponseEntity renameOwner(@RequestBody
                                      RenameNewsletterOwnerName renameNewsletterOwnerNameDTO) {
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String ownerName = newsletter.getOwnerName();
        newsletter.setOwnerName(renameNewsletterOwnerNameDTO.getOwnerName());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok("Renamed owner of " + newsletter.getTitle() + " from " + ownerName + " to " + newsletter.getOwnerName());
    }

    @PostMapping("/rename-email")
    public ResponseEntity renameEmail(@RequestBody
                                      ChangeNewsletterEmail changeNewsletterEmailDTO) {
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newsletter.setEmail(changeNewsletterEmailDTO.getEmail());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok("Renamed recovery email of " + newsletter.getTitle() + " to " + Utils.hideEmail(newsletter.getEmail()));
    }

    @PostMapping("/rename-password")
    public ResponseEntity renamePassword(@RequestBody
                                         ChangeNewsletterPassword changeNewsletterPasswordDTO) {
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
