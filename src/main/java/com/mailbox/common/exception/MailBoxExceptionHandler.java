package com.mailbox.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MailBoxExceptionHandler {

    @ExceptionHandler({AuthException.class})
    public String authException() {
        return "Authentication Error";
    }

    @ExceptionHandler({MailBoxRuntimeException.class})
    public String mailBoxRuntimeException() {
        return "MailBox Runtime Error";
    }

    @ExceptionHandler({UserException.class})
    public String userException() {
        return "User could not be created";
    }

    @ExceptionHandler({NotFoundException.class})
    public String notFoundException() {
        return "Not Found Exception";
    }
}
