package com.libararymanagment.libararymangmentsystem.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(
            String message) {
        super(message);
    }
}
