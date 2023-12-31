package com.jessenerio.email_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class Dispatch {
    public static void main(String[] args) {
        initConfigFile();
        ApplicationContext context = SpringApplication.run(Dispatch.class, args);
//        context.getBean(SendEmail.class).testBroadcast();
    }

    private static void initConfigFile() {
        try {
            Path configFilePath = Paths.get("src/main/resources/config.properties");
            if (!configFilePath.toFile().exists()) {
                String content = String.format("" +
                    "# application.properties\n" +
                    "spring.security.user.name=user\n" +
                    "spring.security.user.password=generated-password\n" +
                    "\n" +
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

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
