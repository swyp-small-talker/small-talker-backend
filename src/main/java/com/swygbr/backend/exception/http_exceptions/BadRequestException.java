package com.swygbr.backend.exception.http_exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
