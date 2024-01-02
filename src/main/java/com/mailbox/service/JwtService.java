package com.mailbox.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

public interface JwtService {
    String extractUser(String token);
    Boolean validateToken(String token, UserDetails userDetails);
    String generateToken(String userName);
}
