package com.jessenerio.email_service.model.dto.newsletter;

import com.jessenerio.email_service.util.Utils;
import lombok.Getter;

@Getter
public class NewsletterSetupMailSender {
    private String username;
    private String host;
    private int port;
    private String protocol;
    private boolean smtpAuth;
    private boolean enableTLS;
    private boolean enableSSL;
    private String password;

    public String getUsername() {
        return Utils.toTitleCase(username);
    }

    public String getPassword() {
        return Utils.decodeBase64(password);
    }
}
