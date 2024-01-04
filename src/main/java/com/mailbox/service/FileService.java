package com.mailbox.service;

import com.mailbox.enums.MailType;

import java.util.List;
import java.util.Map;

public interface FileService {

    Map<MailType,String> fileReadConvertList();
}
