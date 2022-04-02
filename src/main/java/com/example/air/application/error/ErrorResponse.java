package com.example.air.application.error;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private ErrorCode errorCode;
    private String description;
    private String message;

    private ErrorResponse(final ErrorCode errorCode, final String message) {
        this.errorCode = errorCode;
        this.description = errorCode.getDescription();
        this.message = message;
    }

    public static ErrorResponse of(final ErrorCode errorCode) {
        return new ErrorResponse(errorCode, null);
    }

    public static ErrorResponse of(final ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }
}
