package com.noir.webthingauth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UlBadRequestException extends RuntimeException {
    public UlBadRequestException(String message) {
        super(message);
    }
}
