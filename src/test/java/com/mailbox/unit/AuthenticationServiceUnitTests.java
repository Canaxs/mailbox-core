package com.mailbox.unit;

import com.mailbox.persistence.repository.TokenRepository;
import com.mailbox.persistence.repository.UserRepository;
import com.mailbox.service.AuthenticationService;
import com.mailbox.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceUnitTests {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private JwtService jwtService;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void unitTestAuthenticate() {

    }
}
