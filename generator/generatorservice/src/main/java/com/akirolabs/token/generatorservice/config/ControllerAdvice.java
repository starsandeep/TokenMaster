package com.akirolabs.token.generatorservice.config;

import com.akirolabs.token.generatorservice.Exception.BadRequestException;
import com.akirolabs.token.generatorservice.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * @author: Sandeep Kumar Nagaraj
 * @since: 13-08-2023
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public Mono<ResponseEntity<Object>> handleBadRequestException(
            BadRequestException ex, ServerWebExchange exchange) {
        log.error("Bad Request Exception Occurred {}", ex);
        ErrorResponse errorResponse = populateErrorResponse(ex, HttpStatus.BAD_REQUEST, exchange);
        return Mono.just(new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST));
    }

    private ErrorResponse populateErrorResponse(
            Exception e, HttpStatus httpStatus, ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        ErrorResponse errorResponse =
                ErrorResponse.builder()
                        .httpMethod(request.getMethod().name())
                        .requestUri(request.getURI().getPath())
                        .statusCode(httpStatus.value())
                        .statusText(httpStatus.getReasonPhrase())
                        .errorTimestamp(LocalDateTime.now().toString())
                        .errorMessage(
                                e instanceof WebClientResponseException
                                        ? ((WebClientResponseException) e).getResponseBodyAsString()
                                        : e.getMessage())
                        .build();
        return errorResponse;
    }
}

