package com.jessenerio.email_service.service;

import com.jessenerio.email_service.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EMailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EMailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String fromAddress, String toAddress, Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(toAddress);
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        javaMailSender.send(message);
    }

}