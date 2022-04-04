package com.example.air.application.error;

import com.example.air.application.error.exception.CustomNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomNotFoundException.class)
    protected ErrorResponse handleCustomNotFoundException(final CustomNotFoundException e) {
        log.error("handleCustomNotFoundException", e);
        final ErrorCode errorCode = e.getErrorCode();
        String message = String.format("[%s]는 존재하지 않습니다", e.getValue());

        return ErrorResponse.of(errorCode, message);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ErrorResponse handleException(Exception e) {
        log.error("handleException", e);

        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
