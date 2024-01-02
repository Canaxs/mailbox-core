package com.mailbox.service.impl;

import com.mailbox.common.exception.AuthException;
import com.mailbox.models.UserAuthRequest;
import com.mailbox.persistence.entity.User;
import com.mailbox.persistence.repository.UserRepository;
import com.mailbox.service.AuthenticationService;
import com.mailbox.service.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    public AuthenticationServiceImpl(UserRepository userRepository,JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public String authenticate(UserAuthRequest userAuthRequest, Authentication authentication) {
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(userAuthRequest.getUsername());
        }
        throw new AuthException("identity could not be verified");
    }
}
