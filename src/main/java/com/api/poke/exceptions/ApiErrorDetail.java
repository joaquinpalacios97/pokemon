package com.api.poke.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiErrorDetail {
    String field;
    String value;
    String message;
}
