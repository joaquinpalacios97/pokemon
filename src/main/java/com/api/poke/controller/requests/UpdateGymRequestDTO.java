package com.api.poke.controller.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
@Getter
public class UpdateGymRequestDTO {
    @NotEmpty(message = "Name cannot be empty")
    String name;
    @NotEmpty(message = "Type cannot be empty")
    String type;
}
