package com.genenakagaki.checklist.controller.error;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class BadRequestException extends RuntimeException {

    private final ErrorData errorData;

    public BadRequestException(String errorMessage) {
        super();
        this.errorData = new ErrorData(Optional.of(errorMessage), List.of());
    }

    public BadRequestException(String fieldName, String errorMessage) {
        super();
        this.errorData = new ErrorData(Optional.empty(), List.of(
                new ErrorData.FieldError(fieldName, errorMessage)
        ));
    }
}
