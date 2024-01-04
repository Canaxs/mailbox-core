package com.mailbox.controller;

import com.mailbox.models.response.MailInfoResponse;
import com.mailbox.models.response.ResultResponse;
import com.mailbox.models.response.UserCreateResponse;
import com.mailbox.service.MailBoxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@Tag(name="MailBoxController Documentation")
public class MailBoxController {

    private MailBoxService mailBoxService;

    public MailBoxController(MailBoxService mailBoxService) {
        this.mailBoxService = mailBoxService;
    }

    @Operation(
            description = "MailControl Service",
            summary = "Control Mail Service",
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
    @GetMapping("/mailControl")
    public ResponseEntity<ResultResponse> mailControl(){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResult(mailBoxService.mailControl());
        return ResponseEntity.ok(resultResponse);
    }
}
