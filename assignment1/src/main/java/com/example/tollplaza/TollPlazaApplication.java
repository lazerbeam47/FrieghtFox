package com.example.tollplaza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Toll Plaza Spring Boot application.
 * Starts the embedded server and initializes the application context.
 */
@SpringBootApplication
public class TollPlazaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TollPlazaApplication.class, args);
    }
} 