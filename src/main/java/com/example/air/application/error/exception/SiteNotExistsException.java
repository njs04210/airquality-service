package com.example.air.application.error.exception;

import com.example.air.application.error.ErrorCode;

public class SiteNotExistsException extends BusinessException {

    public SiteNotExistsException() {
        super(ErrorCode.INVALID_SITE_NAME);
    }

}
