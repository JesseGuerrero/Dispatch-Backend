package com.jessenerio.email_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class EMailServiceApplication {
//
//    @Autowired
//    private EMailService emailService;

    public static void main(String[] args) {
//        initConfigFile();
        SpringApplication.run(EMailServiceApplication.class, args);
    }

    private static void initConfigFile() {
        try {
            Path configFilePath = Paths.get("src/main/resources/config.properties");
            if (!configFilePath.toFile().exists()) {
                String content = String.format("#Required config\n" +
                        "spring.mail.host=[SMTP]\n" +
                        "spring.mail.port=[SMTP PORT] \n" +
                        "spring.mail.username=[SMTP EMAIL ADDRESS]\n" +
                        "spring.mail.password=[SMTP PASSWORD]\n" +
                        "spring.mail.transport.protocol=smtp\n" +
                        "spring.mail.properties.mail.smtp.auth=true\n" +
                        "spring.mail.properties.mail.smtp.starttls.enable=true\n" +
                        "spring.mail.properties.mail.smtp.ssl.enable=true"
                );
                FileWriter writer = new FileWriter(configFilePath.toFile());
                writer.write(content);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void run(String... args) {
//        // Example: Sending an email when the application starts
//        String to = "jessework@proton.me";
//        String subject = "Test Subject";
//        String body = "Hello, this is a test email.";
//
//        emailService.sendEmail(to, subject, body);
//
//        System.out.println("Email sent successfully!");
//    }
}
