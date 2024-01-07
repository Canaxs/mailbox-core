package com.mailbox.service;

import com.mailbox.models.request.UserAuthRequest;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    String authenticate(UserAuthRequest userAuthRequest,Authentication authentication);

    String logout(String token);
}
