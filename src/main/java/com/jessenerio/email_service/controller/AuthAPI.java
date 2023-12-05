package com.jessenerio.email_service.controller;


import com.jessenerio.email_service.model.document.Newsletter;
import com.jessenerio.email_service.model.dto.LoginDTO;
import com.jessenerio.email_service.model.dto.SignupDTO;
import com.jessenerio.email_service.model.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthAPI {
    @Autowired
    NewsletterService newsletterService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    DaoAuthenticationProvider authenticationManager;

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
        // Authenticate the newsletter
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(newsletter, loginDTO.getPassword())
        );

        // Set the authentication in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("You are logged into newsletter");
    }

}
