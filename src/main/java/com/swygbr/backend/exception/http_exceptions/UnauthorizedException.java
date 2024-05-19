package com.swygbr.backend.exception.http_exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
