package com.libararymanagment.libararymangmentsystem.EXception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(
            String message) {
        super(message);
    }
}
