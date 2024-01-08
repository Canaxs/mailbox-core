package com.mailbox.models.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserAuthRequest {
    private String username;
    private String password;
}
