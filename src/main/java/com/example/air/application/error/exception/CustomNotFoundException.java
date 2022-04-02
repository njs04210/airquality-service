package com.example.air.application.error.exception;

import com.example.air.application.error.ErrorCode;

public class CustomNotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomNotFoundException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
