package com.example.air.application.error.exception;

import com.example.air.application.error.ErrorCode;

public class RegionNotExistsException extends CustomNotFoundException {

    public RegionNotExistsException(String value) {
        super(ErrorCode.NOT_FOUND_REGION, value);
    }
}
