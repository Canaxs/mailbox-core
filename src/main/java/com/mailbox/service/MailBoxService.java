package com.mailbox.service;

import com.mailbox.models.response.MailInfoResponse;

import java.util.List;

public interface MailBoxService {

    List<MailInfoResponse> mailControl();

}
