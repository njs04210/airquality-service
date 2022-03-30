package com.example.air.application.error;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private int status;
    private String code;
    private String message;

    private ErrorResponse(final ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static ErrorResponse of(final ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }
}
