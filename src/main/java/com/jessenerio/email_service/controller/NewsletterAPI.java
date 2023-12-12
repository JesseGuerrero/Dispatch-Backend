package com.jessenerio.email_service.controller;


import com.jessenerio.email_service.model.document.Email;
import com.jessenerio.email_service.model.document.Newsletter;
import com.jessenerio.email_service.model.document.Tag;
import com.jessenerio.email_service.model.dto.newsletter.AddEmail;
import com.jessenerio.email_service.model.dto.newsletter.AddEmailToTags;
import com.jessenerio.email_service.model.dto.newsletter.AddEmptyTag;
import com.jessenerio.email_service.model.dto.newsletter.BroadcastToTag;
import com.jessenerio.email_service.model.dto.newsletter.ChangeNewsletterEmail;
import com.jessenerio.email_service.model.dto.newsletter.ChangeNewsletterPassword;
import com.jessenerio.email_service.model.dto.newsletter.DeleteEmail;
import com.jessenerio.email_service.model.dto.newsletter.DeleteTag;
import com.jessenerio.email_service.model.dto.newsletter.RemoveEmailFromTags;
import com.jessenerio.email_service.model.dto.newsletter.RenameNewsletterOwnerName;
import com.jessenerio.email_service.model.dto.newsletter.RenameNewsletterTitle;
import com.jessenerio.email_service.model.service.EMailService;
import com.jessenerio.email_service.model.service.NewsletterService;
import com.jessenerio.email_service.util.Utils;
import com.nimbusds.jose.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public ResponseEntity broadcast(@RequestBody BroadcastToTag broadcastToTag) {
        Newsletter newsletter = (Newsletter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pair<String, List<String>> emailResult = newsletter.getEmailsFromTags(broadcastToTag.getTags());
        for (String email : emailResult.getRight()) {
            Email emailContent = new Email(broadcastToTag.getSubject(), broadcastToTag.getBody());
            emailService.sendEmail(email, emailContent);
        }
        return ResponseEntity.ok(emailResult.getLeft());
    }

    @PostMapping("/add-tag")
    public ResponseEntity addTag(@RequestBody AddEmptyTag addEmptyTag) {
        if(addEmptyTag.getTags().length == 0)
            return ResponseEntity.badRequest().body("No tags inserted");
        Newsletter newsletter = (Newsletter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for(String tag : addEmptyTag.getTags())
            newsletter.addTag(tag);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/add-email-to-tags")
    public ResponseEntity addEmailToTag(@RequestBody AddEmailToTags addEmailToTags) {
        String email = addEmailToTags.getEmail();
        String[] tags = addEmailToTags.getTags();
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

    @PostMapping("/remove-email-from-tag")
    public ResponseEntity addEmailToTag(@RequestBody RemoveEmailFromTags removeEmailFromTags) {
        String email = removeEmailFromTags.getEmail();
        String[] tags = removeEmailFromTags.getTags();
        if(tags.length == 0)
            return ResponseEntity.badRequest().body("No tags detected from input");
        if(email == "")
            return ResponseEntity.badRequest().body("Email is invalid");
        Newsletter newsletter = (Newsletter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!newsletter.hasEmailInList(email))
            return ResponseEntity.badRequest().body(email + " not in the " + newsletter.getTitle() + " newsletter list");
        StringBuilder successMessage = newsletter.removeEmailFromTags(email, tags);
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok(successMessage);
    }

    @PostMapping("/delete-tags")
    public ResponseEntity deleteTags(@RequestBody DeleteTag deleteTag) {
        if(deleteTag.getTags().length == 0)
            return ResponseEntity.badRequest().body("No tags removed");
        Newsletter newsletter = (Newsletter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for(String tag : deleteTag.getTags())
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
    public ResponseEntity addSubscriber(@RequestBody AddEmail addEmail) {
        String email = addEmail.getEmail();
        if(email == "")
            return ResponseEntity.badRequest().body("Email is invalid");
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(newsletter.isDuplicateEmail(email))
            return ResponseEntity.badRequest().body("Email already exists");
        newsletter.addEmailContact(email, addEmail.getFirstName());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok(email + " added to " + newsletter.getTitle());
    }

    @PostMapping("/remove-subscriber")
    public ResponseEntity addSubscriber(@RequestBody DeleteEmail deleteEmail) {
        String email = deleteEmail.getEmail();
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
    public ResponseEntity renameTitle(@RequestBody RenameNewsletterTitle renameNewsletterTitle) {
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String originalTitle = newsletter.getTitle();
        newsletter.setTitle(renameNewsletterTitle.getTitle());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok("Renamed title of " + originalTitle + " to " + newsletter.getTitle());
    }

    @PostMapping("/rename-owner")
    public ResponseEntity renameOwner(@RequestBody RenameNewsletterOwnerName renameNewsletterOwnerName) {
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String ownerName = newsletter.getOwnerName();
        newsletter.setOwnerName(renameNewsletterOwnerName.getOwnerName());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok("Renamed owner of " + newsletter.getTitle() + " from " + ownerName + " to " + newsletter.getOwnerName());
    }

    @PostMapping("/rename-email")
    public ResponseEntity renameEmail(@RequestBody ChangeNewsletterEmail changeNewsletterEmail) {
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newsletter.setEmail(changeNewsletterEmail.getEmail());
        newsletterService.updateNewsletter(newsletter);
        return ResponseEntity.ok("Renamed recovery email of " + newsletter.getTitle() + " to " + Utils.hideEmail(newsletter.getEmail()));
    }

    @PostMapping("/rename-password")
    public ResponseEntity renamePassword(@RequestBody ChangeNewsletterPassword changeNewsletterPassword) {
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newsletter.setPassword(changeNewsletterPassword.getPassword());
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
