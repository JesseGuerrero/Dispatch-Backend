package com.jessenerio.email_service.dto;

import com.jessenerio.email_service.document.Customer;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
    private String id;
    private String username;

    public static UserDTO from(Customer user) {
        return builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
