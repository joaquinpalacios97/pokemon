package com.api.poke.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ApiError {
    Integer statusCode;
    String code;
    String title;
    String message;


    List<ApiErrorDetail> details = new ArrayList<>();
}
