package com.mailbox.service.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MailInfoDTO {
    private String mailTitle;

    private String mailSubject;

    private String mailType;

    private String mailSender;
}
