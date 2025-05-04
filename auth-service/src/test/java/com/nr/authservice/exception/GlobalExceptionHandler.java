package com.nr.authservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMessageNotValidException(MethodArgumentNotValidException exp) {
        Map<String, String> errors = new HashMap<>();
        exp.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException exp) {
        Map<String, String> errors = Map.of("error", exp.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IncorrectLoginCredentials.class)
    public ResponseEntity<Map<String, String>> handleIncorrectLoginCredentialsø(IncorrectLoginCredentials exp) {
        Map<String, String> errors = Map.of("error", exp.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

}
