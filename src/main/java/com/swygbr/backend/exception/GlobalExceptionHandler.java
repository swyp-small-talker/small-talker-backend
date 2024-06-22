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
import org.springframework.web.servlet.resource.NoResourceFoundException;

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

    // controller나 service에서 발생한 ResponseStatusException 처리
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