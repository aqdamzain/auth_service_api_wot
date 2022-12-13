package com.noir.webthingauth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UlAuthException extends RuntimeException {
    public UlAuthException(String message) {
        super(message);
    }
}
