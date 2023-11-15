package com.catolica.sc.spaum.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@Slf4j
@RestController
public class ApiExceptionHandler{
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Error> handleApiException(ApiException exception) {
        Error error = new Error(exception.getI18n(), ErrorTypeConstants.GENERIC_ERROR);
        return ResponseEntity.status(exception.getHttpStatus()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception exception) {
        Error error = new Error(MessageCodeConstants.ERROR_UNHANDLED_EXCEPTION, ErrorTypeConstants.GENERIC_ERROR);
        log.error("An unhandled exception was caught by the Exception Handler", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
