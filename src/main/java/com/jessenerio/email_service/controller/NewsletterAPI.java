package com.jessenerio.email_service.controller;


import com.jessenerio.email_service.model.document.Email;
import com.jessenerio.email_service.model.document.EmailSettings;
import com.jessenerio.email_service.model.document.Newsletter;
import com.jessenerio.email_service.model.dto.newsletter.AddEmail;
import com.jessenerio.email_service.model.dto.newsletter.AddEmailToTags;
import com.jessenerio.email_service.model.dto.newsletter.AddEmptyTag;
import com.jessenerio.email_service.model.dto.newsletter.BroadcastToTag;
import com.jessenerio.email_service.model.dto.newsletter.ChangeNewsletterEmail;
import com.jessenerio.email_service.model.dto.newsletter.ChangeNewsletterPassword;
import com.jessenerio.email_service.model.dto.newsletter.DeleteEmail;
import com.jessenerio.email_service.model.dto.newsletter.DeleteTag;
import com.jessenerio.email_service.model.dto.newsletter.NewsletterSetupMailSender;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    PasswordEncoder passwordEncoder;

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
        String username = broadcastToTag.getFromUsername();
        if(username == null || username.length() == 0)
            return ResponseEntity.badRequest().body("Username is invalid");
        Newsletter newsletter = (Newsletter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pair<String, List<String>> emailsResult = newsletter.getEmailsFromTags(broadcastToTag.getTags());
        try {
            for (String email : emailsResult.getRight()) {
                Email emailContent = new Email(broadcastToTag.getSubject(), broadcastToTag.getBody());
                emailService.sendEmailFromNewsletter(newsletter, username, email, emailContent);
            }
        } catch(NullPointerException e) {
            return ResponseEntity.badRequest().body("Username unknown");
        }
        return ResponseEntity.ok(emailsResult.getLeft());
    }

    @PostMapping("/add-tag")
    public ResponseEntity addTag(@RequestBody AddEmptyTag addEmptyTag) {
        if(addEmptyTag.getTags().length == 0)
            return ResponseEntity.badRequest().body("No tags inserted");
        Newsletter newsletter = (Newsletter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StringBuilder existMessage = new StringBuilder();
        StringBuilder tagsAdded = new StringBuilder();
        for(String tag : addEmptyTag.getTags())
            if(newsletter.tagExists(tag))
                existMessage.append(tag).append(", ");
            else {
                newsletter.addTag(tag);
                tagsAdded.append(tag).append(", ");
            }
        newsletterService.updateNewsletter(newsletter);
        StringBuilder successMessage = new StringBuilder();
        if(existMessage.length() > 0)
            successMessage.append("Tags already exist: ").append(existMessage + "\n");
        if(tagsAdded.length() > 0)
            successMessage.append("Tags added: ").append(tagsAdded + "\n");

        return ResponseEntity.ok("Success \n" + successMessage);
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
        StringBuilder dontExistMessage = new StringBuilder();
        StringBuilder tagsDeleted = new StringBuilder();
        for(String tag : deleteTag.getTags())
            if(!newsletter.tagExists(tag))
                dontExistMessage.append(tag).append(", ");
            else {
                newsletter.removeTag(tag);
                tagsDeleted.append(tag).append(", ");
            }
        newsletterService.updateNewsletter(newsletter);
        StringBuilder successMessage = new StringBuilder();
        if(dontExistMessage.length() > 0)
            successMessage.append("Tags do not exist: ").append(dontExistMessage + "\n");
        if(tagsDeleted.length() > 0)
            successMessage.append("Tags deleted: ").append(tagsDeleted + "\n");
        return ResponseEntity.ok("Success\n" + successMessage);
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
        newsletterService.setPassword(changeNewsletterPassword.getPassword());
        return ResponseEntity.ok("Successfully updated password");
    }

    @PostMapping("/setup-newsletter-email")
    public ResponseEntity setupNewsletterEmail(@RequestBody NewsletterSetupMailSender newsletterSetupMailSender) {
        String email = newsletterSetupMailSender.getEmail();
        String username = newsletterSetupMailSender.getUsername();
        if(email == null || email.length() == 0)
            return ResponseEntity.badRequest().body("Email is invalid");
        if(username == null || username.length() == 0)
            return ResponseEntity.badRequest().body("Username is invalid");
        Newsletter newsletter = (Newsletter)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        EmailSettings emailSettings = new EmailSettings();
        emailSettings.setUsername(email);
        emailSettings.setHost(newsletterSetupMailSender.getHost());
        emailSettings.setPort(newsletterSetupMailSender.getPort());
        emailSettings.setProtocol(newsletterSetupMailSender.getProtocol());
        emailSettings.setSmtpAuth(newsletterSetupMailSender.isSmtpAuth());
        emailSettings.setEnableTLS(newsletterSetupMailSender.isEnableTLS());
        emailSettings.setEnableSSL(newsletterSetupMailSender.isEnableSSL());
        emailSettings.setPassword(newsletterSetupMailSender.getPassword());
        newsletter.setEmailSettings(username, emailSettings);
        newsletterService.updateNewsletter(newsletter);
        emailService.sendEmailFromNewsletter(newsletter, username, email, new Email("Newsletter Email Settings", "Successfully updated newsletter email settings."));
        return ResponseEntity.ok("Successfully updated newsletter email settings. A test email was sent to " + email);
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
