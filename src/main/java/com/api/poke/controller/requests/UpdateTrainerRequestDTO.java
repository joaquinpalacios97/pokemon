package com.api.poke.controller.requests;

import com.api.poke.model.Gym;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class UpdateTrainerRequestDTO {

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotEmpty(message = "Gym cannot be empty")
    Gym gym;
}
