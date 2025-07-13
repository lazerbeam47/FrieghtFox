package com.example.tollplaza.exception;

/**
 * Custom exception for API errors.
 * Used to signal business logic or validation failures in the API.
 */
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
} 