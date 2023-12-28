package com.mailbox.controller;

import com.mailbox.service.MailBoxService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mailbox")
public class MailBoxController {

    private MailBoxService mailBoxService;

    public MailBoxController(MailBoxService mailBoxService) {
        this.mailBoxService = mailBoxService;
    }

    @GetMapping("/mailControl")
    public String mailControl(){
        return mailBoxService.mailControl("mailboxspring@gmail.com","ptqn cqmd iuik tkcp");
    }
}
