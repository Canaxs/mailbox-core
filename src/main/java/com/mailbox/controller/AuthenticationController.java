package com.mailbox.controller;

import com.mailbox.models.request.UserAuthRequest;
import com.mailbox.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name="Authentication Controller Documentation")
public class AuthenticationController {

    private final AuthenticationService authenticateService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationService authenticateService,AuthenticationManager authenticationManager) {
        this.authenticateService = authenticateService;
        this.authenticationManager = authenticationManager;
    }

    @Operation(
            description = "Login Service",
            summary = "Login and GenerateJwtToken Service",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid",
                            responseCode = "403"
                    )
            }
    )
    @PostMapping("/login")
    private String isLogged(@RequestBody UserAuthRequest userAuthRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthRequest.getUsername(), userAuthRequest.getPassword()));
        return authenticateService.authenticate(userAuthRequest,authentication);
    }

    @Operation(
            description = "Logout Service",
            summary = "Logout Service",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid",
                            responseCode = "403"
                    )
            }
    )
    @PostMapping("/logout")
    String handleLogout(@RequestHeader(name = "Authorization") String authorization) {
        return authenticateService.logout(authorization.substring(7));
    }
}
