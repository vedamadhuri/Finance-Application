package com.application.exception;

public class CustomException extends Exception {
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public CustomException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public CustomException() {
        super();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
