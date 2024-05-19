package com.swygbr.backend.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> onHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ErrorDetails errorDetails;
        if (ex.getCause() instanceof MismatchedInputException mismatchedInputException) {
            errorDetails = new ErrorDetails(
                    new Date(),
                    ex.getMessage(),
                    mismatchedInputException.getPath().get(0).getFieldName() + " 필드의 값이 잘못되었습니다.");
        } else {
            errorDetails = new ErrorDetails(
                    new Date(),
                    ex.getMessage(),
                    "확인할 수 없는 형태의 데이터가 들어왔습니다");
        }
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorDetails errorDetails;
        errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                ex.getBindingResult().getFieldError().getField() + ": "
                        + ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
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

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleGlobalException(ResponseStatusException ex) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                ex.getMessage(),
                ex.getReason());
        return new ResponseEntity<>(errorDetails, ex.getStatusCode());
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