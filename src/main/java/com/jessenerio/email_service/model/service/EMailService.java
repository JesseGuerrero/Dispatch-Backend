package com.jessenerio.email_service.model.service;

import com.jessenerio.email_service.model.document.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EMailService {

    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailFromAddress;

    @Autowired
    public EMailService(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String emailFromAddress) {
        this.emailFromAddress = emailFromAddress;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String toAddress, Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFromAddress);
        message.setTo(toAddress);
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        javaMailSender.send(message);
    }

}