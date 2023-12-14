package com.jessenerio.email_service.model.document;

import com.jessenerio.email_service.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSettings {
    private String username = "";
    private String host = "";
    private int port = 0;
    private String protocol = "";
    private boolean smtpAuth = true;
    private boolean enableTLS = true;
    private boolean enableSSL = true;
    private String password = "";
    public String getUsername() {
        return Utils.toTitleCase(username);
    }

    public String getPassword() {
        return password;
    }
}
