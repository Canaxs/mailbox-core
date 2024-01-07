package com.mailbox.service.impl;

import com.mailbox.common.exception.AuthException;
import com.mailbox.models.request.UserAuthRequest;
import com.mailbox.persistence.entity.Token;
import com.mailbox.persistence.repository.TokenRepository;
import com.mailbox.persistence.repository.UserRepository;
import com.mailbox.service.AuthenticationService;
import com.mailbox.service.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final JwtService jwtService;

    public AuthenticationServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
    }

    @Override
    public String authenticate(UserAuthRequest userAuthRequest, Authentication authentication) {
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(userAuthRequest.getUsername());
        }
        throw new AuthException("identity could not be verified");
    }

    @Override
    public String logout(String token) {
        Boolean tokenValidate = jwtService.validateExpiration(token);
        if(!tokenValidate) {
            Token tokenModel = tokenRepository.getTokenByToken(token);
            tokenRepository.delete(tokenModel);
        }
        return "Token has been deleted";
    }
}
