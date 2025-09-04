package com.data.jpa.exceptions;

public class StudentDoesNotExistsException extends RuntimeException {
    public StudentDoesNotExistsException(String message) {
        super(message);
    }
}
