package com.mailbox.controller;

import com.mailbox.common.exception.UserException;
import com.mailbox.models.request.UserCreateRequest;
import com.mailbox.models.response.ResultResponse;
import com.mailbox.models.response.UserCreateResponse;
import com.mailbox.persistence.entity.User;
import com.mailbox.service.UserService;
import com.mailbox.service.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name="User Controller Documentation")
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {

        this.userService = userService;
    }

    @Operation(
            description = "Create Service",
            summary = "Create new user Service",
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
    @PostMapping("/create")
    private ResponseEntity<ResultResponse> userCreate(@RequestBody UserCreateRequest userCreateRequest) {
        ResultResponse resultResponse = new ResultResponse();
        List<UserCreateResponse> createResponses = new ArrayList<>();
        User user = userService.createUser(userCreateRequest);
        createResponses.add(UserCreateResponse.builder().id(String.valueOf(user.getId())).username(user.getUsername()).password(user.getPassword()).build());
        resultResponse.setResult(createResponses);
        return ResponseEntity.ok(resultResponse);
    }
}
