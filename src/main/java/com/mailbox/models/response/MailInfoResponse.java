package com.mailbox.models.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@Builder
public class MailInfoResponse {
    private String mailSender;
    private String mailTitle;
    private String mailSubject;
    private String mailType;
    private Date mailSendDate;
}
