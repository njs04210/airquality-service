package com.example.air.application.error;

import com.example.air.application.error.exception.CustomNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ErrorResponse handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        Object value = e.getValue();
        String message = String.format("[%s]는 존재하지 않는 시(도)명입니다.", value);

        return ErrorResponse.of(ErrorCode.INVALID_REGION_NAME_ERROR, message);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomNotFoundException.class)
    protected ErrorResponse handleCustomNotFoundException(final CustomNotFoundException e) {
        log.error("handleCustomNotFoundException", e);
        final ErrorCode errorCode = e.getErrorCode();

        return ErrorResponse.of(errorCode);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ErrorResponse handleException(Exception e) {
        log.error("handleException", e);

        return ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
