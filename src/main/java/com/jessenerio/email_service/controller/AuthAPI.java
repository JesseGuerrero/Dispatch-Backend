package com.jessenerio.email_service.controller;


import com.jessenerio.email_service.model.document.Email;
import com.jessenerio.email_service.model.document.Newsletter;
import com.jessenerio.email_service.model.dto.ForgotPasswordDTO;
import com.jessenerio.email_service.model.dto.LoginDTO;
import com.jessenerio.email_service.model.dto.SignupDTO;
import com.jessenerio.email_service.model.service.EMailService;
import com.jessenerio.email_service.model.service.NewsletterService;
import com.jessenerio.email_service.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthAPI {
    private final NewsletterService newsletterService;
    private final PasswordEncoder passwordEncoder;
    private final DaoAuthenticationProvider authenticationManager;
    private final EMailService emailService;

    @Autowired
    public AuthAPI(
            NewsletterService newsletterService,
            PasswordEncoder passwordEncoder,
            DaoAuthenticationProvider authenticationManager,
            EMailService emailService) {
        this.newsletterService = newsletterService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody SignupDTO signupDTO) {
        if (newsletterService.isDuplicateNewsletter(signupDTO.getTitle()))
            return ResponseEntity.badRequest().body("Newsletter title already exists");

        //register newsletter
        Newsletter newsletter = new Newsletter(signupDTO.getTitle(), signupDTO.getOwnerName(), signupDTO.getEmail(), signupDTO.getPassword());
        newsletterService.createNewsletter(newsletter);

        return ResponseEntity.ok("Newsletter created");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();

        return ResponseEntity.ok("You are logged out of the newsletter");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        Newsletter newsletter = newsletterService.getNewsLetterByTitle(loginDTO.getTitle());
        if (newsletter == null)
            return ResponseEntity.badRequest().body("Cannot find newsletter with that title");
        boolean isValidLogin = newsletterService.convertTemporaryPasswordToPassword(
                loginDTO.getTitle(),
                loginDTO.getPassword());
        if(isValidLogin) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(newsletter, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("You are logged into newsletter");
        } else {
            return ResponseEntity.badRequest().body("Invalid password");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> loginTemp(@RequestBody Map<String, String> requestBody) {
        String title = Utils.toTitleCase(requestBody.get("title"));
        if (title == null || title.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
        Newsletter newsletter = newsletterService.getNewsLetterByTitle(title);
        if (newsletter == null)
            return ResponseEntity.badRequest().body("Cannot find newsletter with that title");
        String temporaryPassword = Utils.generateRandomString();
        String email = Utils.hideEmail(newsletter.getEmail());
        newsletterService.setTemporaryPassword(newsletter.getTitle(), passwordEncoder.encode(temporaryPassword));
        emailService.sendEmail(newsletter.getEmail(), new Email("Temporary Password",
                temporaryPassword));
        return ResponseEntity.badRequest().body("A new temporary password was sent to " + email + ". Please login with it.");
    }

}
