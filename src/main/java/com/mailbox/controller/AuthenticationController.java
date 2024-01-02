package com.mailbox.controller;

import com.mailbox.common.exception.AuthException;
import com.mailbox.models.UserAuthRequest;
import com.mailbox.service.AuthenticationService;
import com.mailbox.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"${mailbox.ui.address}"})
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticateService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationService authenticateService,AuthenticationManager authenticationManager) {
        this.authenticateService = authenticateService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    private String isLogged(@RequestBody UserAuthRequest userAuthRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthRequest.getUsername(), userAuthRequest.getPassword()));
        return authenticateService.authenticate(userAuthRequest,authentication);
    }

    @PostMapping("/logout")
    String handleLogout(@RequestHeader(name = "Authorization") String authorization) {
        return null;
    }
}
