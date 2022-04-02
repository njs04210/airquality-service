package com.example.air.application.error.exception;

import com.example.air.application.error.ErrorCode;

public class SiteNotExistsException extends CustomNotFoundException {

    public SiteNotExistsException() {
        super(ErrorCode.NO_SITE_NAME_ERROR);
    }

}
