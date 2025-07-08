package com.example.hospitalmanagement.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<Map<String, String>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return Mono.just(error);
    }

    @ExceptionHandler(ServerWebInputException.class)
    public Mono<Map<String, String>> handleValidation(ServerWebInputException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Validation failed: " + ex.getReason());
        return Mono.just(error);
    }

    @ExceptionHandler(Exception.class)
    public Mono<Map<String, String>> handleGeneric(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Unexpected error: " + ex.getMessage());
        return Mono.just(error);
    }
}
