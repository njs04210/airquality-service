package com.example.air.application.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_REGION_NAME(400, "C001", "Invalid Region Name"),
    INVALID_SITE_NAME(400, "C002", "Invalid Site Name"),
    INTERNAL_SERVER_ERROR(500, "C003", "Internal Server Error");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
