package com.catolica.sc.spaum.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class ApiException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private String i18n;

    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    ApiException(String i18n) {
        super(i18n);
        this.i18n = i18n;
    }

    ApiException(String i18n, HttpStatus httpStatus) {
        super(i18n);
        this.i18n = i18n;
        this.httpStatus = httpStatus;
    }
}

