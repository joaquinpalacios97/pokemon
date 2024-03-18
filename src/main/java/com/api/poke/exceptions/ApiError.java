package com.api.poke.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiError {
    Integer statusCode;
    String code;
    String title;
    String message;
}
