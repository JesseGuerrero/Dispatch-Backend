package com.jessenerio.email_service.model.dto.newsletter;

import com.jessenerio.email_service.util.Utils;
import lombok.Getter;

@Getter
public class NewsletterSetupMailSender {
    private String username;
    private String email;
    private String host;
    private int port;
    private String protocol;
    private String smtpAuth;
    private String enableTLS;
    private String enableSSL;
    private String password;

    public String getUsername() {
        return Utils.toTitleCase(username);
    }

    public String getPassword() {
        return Utils.decodeBase64(password);
    }

    public boolean isSmtpAuth() {
        return smtpAuth != null;
    }

    public boolean isEnableTLS() {
        return enableTLS != null;
    }

    public boolean isEnableSSL() {
        return enableSSL != null;
    }

    public String getEmail() {
        if(Utils.isValidEmail(email))
            return email.toLowerCase();
        return "";
    }
}
