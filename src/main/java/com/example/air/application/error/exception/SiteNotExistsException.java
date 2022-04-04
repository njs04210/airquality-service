package com.example.air.application.error.exception;

import com.example.air.application.error.ErrorCode;

public class SiteNotExistsException extends CustomNotFoundException {

    public SiteNotExistsException(Object value) {
        super(ErrorCode.NOT_FOUND_SITE, value);
    }

}
