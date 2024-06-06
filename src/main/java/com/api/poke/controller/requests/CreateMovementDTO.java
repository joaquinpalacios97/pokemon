package com.api.poke.controller.requests;

import com.api.poke.model.PokemonType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true)
@Getter
public class CreateMovementDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Type cannot be null")
    private PokemonType type;

    @NotNull(message = "Power cannot be null")
    private int power;

    @NotNull(message = "Accuracy cannot be null")
    private int accuracy;

    @NotNull(message = "Pp cannot be null")
    private int pp;
}
