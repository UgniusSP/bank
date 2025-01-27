package com.accenture.bank.common.exception;

public class InvalidFundsException extends RuntimeException {
    public InvalidFundsException(String message) {
        super(message);
    }
}
