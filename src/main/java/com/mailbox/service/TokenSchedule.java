package com.mailbox.service;

import com.mailbox.persistence.entity.Token;
import com.mailbox.persistence.repository.TokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TokenSchedule {

    private TokenRepository tokenRepository;

    private JwtService jwtService;

    public TokenSchedule(TokenRepository tokenRepository,JwtService jwtService) {
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
    }

    @Scheduled(fixedDelay = 2160000) // It will run every 60 minutes
    public void scheduleTokenDelete() {
        List<Token> tokens = new ArrayList<>();
        tokens = tokenRepository.findAll();
        for(Token token : tokens) {
            Boolean tokenValidate = jwtService.validateExpiration(token.getToken());
            if(!tokenValidate) {
                tokenRepository.delete(token);
            }
        }
    }
}
