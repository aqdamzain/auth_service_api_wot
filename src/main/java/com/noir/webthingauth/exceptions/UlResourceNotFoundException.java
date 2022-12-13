package com.noir.webthingauth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UlResourceNotFoundException extends RuntimeException {

    public UlResourceNotFoundException(String message) {
        super(message);
    }
}
