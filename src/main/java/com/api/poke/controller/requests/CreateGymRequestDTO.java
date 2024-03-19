package com.api.poke.controller.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true)
public class CreateGymRequestDTO {

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotEmpty(message = "Type cannot be empty")
    String type;
}
