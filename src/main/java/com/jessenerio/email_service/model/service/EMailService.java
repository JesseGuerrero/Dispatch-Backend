package com.jessenerio.email_service.model.service;

import com.jessenerio.email_service.model.document.Email;
import com.jessenerio.email_service.model.document.Newsletter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EMailService {

    private JavaMailSender adminJavaMailSender;

    private String emailFromAddress;

    private String fromNickname;

    @Autowired
    public EMailService(JavaMailSender adminJavaMailSender,
                        @Value("${spring.mail.username}") String emailFromAddress,
                        @Value("${spring.mail.nickname}") String fromNickname) {
        this.fromNickname = fromNickname;
        this.emailFromAddress = emailFromAddress;
        this.adminJavaMailSender = adminJavaMailSender;
    }

    public void sendEmailFromAdmin(String toAddress, Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String address = fromNickname == null || fromNickname.length() == 0 ? emailFromAddress : fromNickname + " <" + emailFromAddress + ">";
        message.setFrom(address);
        message.setTo(toAddress);
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        adminJavaMailSender.send(message);
    }

    public void sendEmailFromNewsletter(Newsletter newsletter, String username, String toAddress, Email email) {
        JavaMailSenderImpl newsletterEmail = newsletter.getNewsletterUserEmail(username);
        try {
            newsletterEmail.testConnection();
        } catch (MessagingException m) {
            sendEmailFromAdmin(newsletter.getEmail(),
                    new Email(
                            "Newsletter " + newsletter.getTitle() + " has invalid smtp settings",
                            "admin email sent instead"
                    ));
            sendEmailFromAdmin(toAddress, email);
            return;
        }
        SimpleMailMessage message = new SimpleMailMessage();
        String fromAddress = newsletterEmail.getUsername(); //Actually the email address
        String fromNickname = username;
        String address = fromNickname == null || fromNickname.length() == 0 ? fromAddress : fromNickname + " <" + fromAddress + ">";
        message.setFrom(address);
        message.setTo(toAddress);
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        newsletterEmail.send(message);
    }

}