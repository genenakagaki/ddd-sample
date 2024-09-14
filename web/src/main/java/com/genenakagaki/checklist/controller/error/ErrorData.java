package com.genenakagaki.checklist.controller.error;

import java.util.List;
import java.util.Optional;

public record ErrorData(Optional<String> errorMessage, List<FieldError> fieldErrors) {

    public record FieldError(String fieldName, String errorMessage) {}
}
