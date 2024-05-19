package com.swygbr.backend.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.swygbr.backend.exception.http_exceptions.BadRequestException;
import com.swygbr.backend.exception.http_exceptions.ForbiddenException;
import com.swygbr.backend.exception.http_exceptions.InternalServerErrorException;
import com.swygbr.backend.exception.http_exceptions.ResourceNotFoundException;
import com.swygbr.backend.exception.http_exceptions.UnauthorizedException;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Resource Not Found Exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Bad Request Exception
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Unauthorized Access Exception
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    // Forbidden Access Exception
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    // Internal Server Error Exception
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<?> handleInternalServerErrorException(InternalServerErrorException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Generic Exception handler for other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}