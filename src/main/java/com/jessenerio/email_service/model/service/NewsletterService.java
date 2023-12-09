package com.jessenerio.email_service.model.service;

import com.jessenerio.email_service.model.document.Newsletter;
import com.jessenerio.email_service.model.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service//Remember a service is a container of logic with no state
public class NewsletterService implements UserDetailsManager {

    @Autowired
    private NewsletterRepository newsletterRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDetails newsletter) {
        ((Newsletter) newsletter).setPassword(passwordEncoder.encode(newsletter.getPassword()));
        newsletterRepository.createNewsletter((Newsletter) newsletter);
    }

    public void createNewsletter(UserDetails newsletter) {
        createUser(newsletter);
    }

    @Override
    public void updateUser(UserDetails newsletter) {
        newsletterRepository.updateNewsletter(((Newsletter) newsletter).getId(), (Newsletter) newsletter);
    }

    public void updateNewsletter(UserDetails newsletter) {
        updateUser(newsletter);
    }

    @Override
    public void deleteUser(String id) {
        newsletterRepository.deleteNewsletter(id);
    }

    public boolean convertTemporaryPasswordToPassword(String title, String password) {
        Newsletter newsletter = newsletterRepository.findByTitle(title);
        System.out.println("pass " + password + " temp " + newsletter.getTemporaryPassword());
        System.out.println("pass " + password + " original " + newsletter.getPassword());
        return passwordEncoder.matches(password, newsletter.getTemporaryPassword()) ||
               passwordEncoder.matches(password, newsletter.getPassword());
    }

    public void deleteNewsletter(String id) {
        deleteUser(id);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // Get the currently authenticated newsletter
        Newsletter newsletter = (Newsletter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        // Check if the old password matches the stored password
        if (!passwordEncoder.matches(oldPassword, newsletter.getPassword())) {
            throw new BadCredentialsException("Old password is incorrect");
        }

        // Encode and set the new password
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        newsletter.setPassword(encodedNewPassword);

        // Save the updated newsletter to the repository
        newsletterRepository.updateNewsletter(newsletter.getId(), newsletter);
    }

    @Override
    public boolean userExists(String title) {
        return newsletterRepository.existsByTitle(title);
    }

    public boolean isDuplicateNewsletter(String title) {
        return userExists(title);
    }

    @Override
    public UserDetails loadUserByUsername(String title)  {
        return newsletterRepository.findByTitle(title);
    }

    public Newsletter getNewsLetterByTitle(String title) {
        return (Newsletter) loadUserByUsername(title);
    }

    public void setTemporaryPassword(String title, String temporaryPassword) {
        newsletterRepository.setTemporaryPassword(title, temporaryPassword);
    }
}