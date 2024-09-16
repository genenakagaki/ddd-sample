package com.genenakagaki.checklist.controller.error;

import lombok.Getter;

import java.util.Map;
import java.util.Optional;

@Getter
public class BadRequestException extends RuntimeException {

    private final ErrorData errorData;

    public BadRequestException(String errorMessage) {
        super();
        this.errorData = new ErrorData(Optional.of(errorMessage), Map.of());
    }

    public BadRequestException(Map<String, String> fieldNameByErrorMessage) {
        super();
        this.errorData = new ErrorData(Optional.empty(), fieldNameByErrorMessage);
    }

    public BadRequestException(String fieldName, String errorMessage) {
        super();
        this.errorData = new ErrorData(Optional.empty(), Map.of(
                fieldName, errorMessage
        ));
    }
}
