package com.mailbox.models.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserCreateResponse {
    private String id;
    private String username;
    private String password;
}
