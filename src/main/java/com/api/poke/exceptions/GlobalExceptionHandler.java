package com.api.poke.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.Objects;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {PokemonNotFoundException.class})
    protected ResponseEntity<Object> handlePokemonNotFoundException(
            PokemonNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiError.setCode("NOT_FOUND");
        apiError.setTitle("Not Found");
        apiError.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, apiError,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiError.setCode("BAD_REQUEST");
        apiError.setTitle("Validation Error");
        apiError.setMessage("Invalid request content.");
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            ApiErrorDetail apiErrorDetail = new ApiErrorDetail();
            apiErrorDetail.setValue(error.getRejectedValue() != null ? error.getRejectedValue().toString() : null);
            apiErrorDetail.setField(error.getField());
            apiErrorDetail.setMessage(error.getDefaultMessage());
            apiError.getDetails().add(apiErrorDetail);
        });

        return handleExceptionInternal(ex, apiError,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}