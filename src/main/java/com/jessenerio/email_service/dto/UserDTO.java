package com.jessenerio.email_service.dto;

import com.jessenerio.email_service.document.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
    private String id;
    private String email;
    private String password;

    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
