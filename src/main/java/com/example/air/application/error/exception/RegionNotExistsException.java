package com.example.air.application.error.exception;

import com.example.air.application.error.ErrorCode;

public class RegionNotExistsException extends BusinessException {

    public RegionNotExistsException() {
        super(ErrorCode.INVALID_REGION_NAME);
    }
}
