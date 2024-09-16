package com.genenakagaki.checklist.controller.error;

import java.util.Map;
import java.util.Optional;

public record ErrorData(Optional<String> errorMessage, Map<String, String> fieldNameByErrorMessage) {
}
