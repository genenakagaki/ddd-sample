package com.genenakagaki.checklist.domain;

public class DomainValidationException extends Exception {

    public static final String DEFAULT_MESSAGE = "An unexpected error occurred. Please try again later";

    private final String validationErrorMessage;

    public DomainValidationException(String validationErrorMessage) {
        super(validationErrorMessage);
        this.validationErrorMessage = validationErrorMessage;
    }

    public DomainValidationException() {
        super(DEFAULT_MESSAGE);
        this.validationErrorMessage = DEFAULT_MESSAGE;
    }

    public String getValidationErrorMessage() {
        return validationErrorMessage;
    }
}
