package com.genenakagaki.checklist.controller.error;

import com.genenakagaki.checklist.domain.DomainValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorData> onWebExchangeBindException(WebExchangeBindException e) {
        return Mono.defer(() -> {
            Map<String, String> fieldNameByErrorMessage = e.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(field -> field.getField(), field -> field.getDefaultMessage()));

            if (fieldNameByErrorMessage.isEmpty()) {
                return Mono.just(new ErrorData(Optional.of(DomainValidationException.DEFAULT_MESSAGE), Map.of()));
            }

            return Mono.just(new ErrorData(Optional.empty(), fieldNameByErrorMessage));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorData> onBadRequestException(BadRequestException e) {
        return Mono.defer(() -> {
            return Mono.just(e.errorData());
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
