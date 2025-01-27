package com.accenture.bank.common.exception;

public class InvalidUserIdException extends RuntimeException {
    public InvalidUserIdException(String message) {
        super(message);
    }
}
