package com.api.poke.controller.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
@Getter
public class UpdateMovementDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Type cannot be empty")
    private String type;

    @NotNull(message = "Power cannot be null")
    private int power;

    @NotNull(message = "Accuracy cannot be null")
    private int accuracy;

    @NotNull(message = "Pp cannot be null")
    private int pp;
}
