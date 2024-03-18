package com.api.poke.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {PokemonNotFoundException.class })
    protected ResponseEntity<Object> handlePokemonNotFoundException(
            PokemonNotFoundException ex, WebRequest request){
        ApiError apiError = new ApiError();
        apiError.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiError.setCode("NOT_FOUND");
        apiError.setTitle("Not Found");
        apiError.setMessage(ex.getMessage());
        return handleExceptionInternal(ex,apiError,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}