package com.almundo.call_center.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServiceException extends RuntimeException {

    public InternalServiceException(String message, Throwable exception) {
        super(message, exception);
    }

}
