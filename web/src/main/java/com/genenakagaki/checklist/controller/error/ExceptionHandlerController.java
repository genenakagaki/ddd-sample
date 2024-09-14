package com.genenakagaki.checklist.controller.error;

import com.genenakagaki.checklist.domain.DomainValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorData> onBadRequest(WebExchangeBindException e) {
        return Mono.defer(() -> {
            List<ErrorData.FieldError> fieldErrors = e.getFieldErrors()
                    .stream()
                    .map(field -> new ErrorData.FieldError(field.getDefaultMessage(), field.getField()))
                    .toList();

            if (fieldErrors.isEmpty()) {
                return Mono.just(new ErrorData(Optional.of(DomainValidationException.DEFAULT_MESSAGE), List.of()));
            }

            return Mono.just(new ErrorData(Optional.empty(), fieldErrors));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorData> onBadRequest(BadRequestException e) {
        return Mono.defer(() -> {
            return Mono.just(e.errorData());
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
