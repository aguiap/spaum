package com.catolica.sc.spaum.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Error {
    private String message;

    private String type = ErrorTypeConstants.GENERIC_ERROR;
}

