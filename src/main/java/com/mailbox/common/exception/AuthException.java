package com.mailbox.common.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public class AuthException extends RuntimeException{
    public AuthException(String message) {
        super(String.format("AuthException: %s", message));
    }
}
