package com.assignment.spring.exceptionhandler.advice;

import com.assignment.spring.api.model.ErrorDTO;
import com.assignment.spring.exceptionhandler.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDTO> handleSizeException(ResponseStatusException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(buildResourceNotFoundException(buildResourceNotFoundExceptionMessage(exception)));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildResourceNotFoundException(exception.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildResourceNotFoundException(exception.getMessage()));
    }

    private ErrorDTO buildResourceNotFoundException(String message) {
        return ErrorDTO.builder()
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    private String buildResourceNotFoundExceptionMessage(ResponseStatusException exception) {
        if (exception.getStatus().equals(HttpStatus.BAD_REQUEST)) {
            return "Could not find the provided city.";
        }
        return "There has been an ResourceNotFoundException while processing your request.";
    }

}
