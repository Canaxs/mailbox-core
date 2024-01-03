package com.mailbox.controller;

import com.mailbox.service.MailBoxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String mailControl(){
        return mailBoxService.mailControl("mailboxspring@gmail.com","ptqn cqmd iuik tkcp");
    }
}
