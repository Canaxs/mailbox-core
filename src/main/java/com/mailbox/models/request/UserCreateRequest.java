package com.mailbox.models.request;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserCreateRequest {
    private String username;
    private String password;
    private String mailAddress;
    private String mailPassword;
}
