package com.mailbox.common.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public class MailBoxRuntimeException extends RuntimeException{
    public MailBoxRuntimeException(String message) {
        super(String.format("MailBoxRuntimeException: %s", message));
    }
}
