package com.example.air.application.error.exception;

import com.example.air.application.error.ErrorCode;

public class OpenApiNotExistsException extends CustomNotFoundException {

    public OpenApiNotExistsException() {
        super(ErrorCode.NO_OPENAPI_SERVICE_ERROR);
    }
}
