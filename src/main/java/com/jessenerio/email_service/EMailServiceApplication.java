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
        initConfigFile();
        SpringApplication.run(EMailServiceApplication.class, args);
    }

    private static void initConfigFile() {
        try {
            Path configFilePath = Paths.get("src/main/resources/config.properties");
            if (!configFilePath.toFile().exists()) {
                String content = String.format("" +
                    "#E-Mail Configuration\n" +
                    "spring.mail.host=smtp.example.com\n" +
                    "spring.mail.port=465\n" +
                    "spring.mail.username=contact@gmail.com\n" +
                    "spring.mail.password=password\n" +
                    "spring.mail.transport.protocol=smtp\n" +
                    "spring.mail.properties.mail.smtp.auth=true\n" +
                    "spring.mail.properties.mail.smtp.starttls.enable=true\n" +
                    "spring.mail.properties.mail.smtp.ssl.enable=true\n" +
                    "\n" +
                    "#MongoDB Configuration\n" +
                    "spring.data.mongodb.host=localhost\n" +
                    "spring.data.mongodb.port=27017\n" +
                    "spring.data.mongodb.database=email-service\n" +
                    "spring.data.mongodb.username=\n" +
                    "spring.data.mongodb.password=\n" +
                    "\n" +
                    "spring.web.resources.add-mappings=true\n"
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
