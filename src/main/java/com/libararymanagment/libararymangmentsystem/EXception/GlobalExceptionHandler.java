package com.libararymanagment.libararymangmentsystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(
            UserAlreadyExistsException.class)
    public ResponseEntity<String>
    handleUserExists(
            UserAlreadyExistsException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
    @ExceptionHandler(
            MethodArgumentNotValidException.class)
    public Map<String, String>
    handleValidation(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors =
                new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()));
        return errors;
    }

}
