package com.application.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ExcessAmountException extends Exception {
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public ExcessAmountException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ExcessAmountException(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }
}
