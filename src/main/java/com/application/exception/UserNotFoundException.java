package com.application.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public UserNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public UserNotFoundException(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}


