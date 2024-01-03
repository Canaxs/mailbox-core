package com.mailbox.models.response;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ResultResponse {
    private List<?> result;
    private Long statusCode;
    private String message;

    public void setResult(List<?> result) {
        this.result = result;
        this.statusCode = 200L;
        this.message = "OK";
    }
}
