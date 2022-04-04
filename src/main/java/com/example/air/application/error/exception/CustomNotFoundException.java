package com.example.air.application.error.exception;

import com.example.air.application.error.ErrorCode;

public class CustomNotFoundException extends RuntimeException {

    private ErrorCode errorCode;
    private final Object value;

    public CustomNotFoundException(ErrorCode errorCode, Object value) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.value = value;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Object getValue() {
        return value;
    }
}
