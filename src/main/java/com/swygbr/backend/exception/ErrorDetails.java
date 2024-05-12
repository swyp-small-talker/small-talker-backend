package com.swygbr.backend.exception;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorDetails {
    private final Date timestamp;
    private final String message;
    private final String details;
}