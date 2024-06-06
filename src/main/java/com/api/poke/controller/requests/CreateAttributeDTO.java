package com.api.poke.controller.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true)
@Getter
public class CreateAttributeDTO {
    @NotNull(message = "HP cannot be null")
    @Min(value = 0)
    int hp;

    @NotNull(message = "Attack cannot be null")
    @Min(value = 0)
    int attack;

    @NotNull(message = "Defense cannot be null")
    @Min(value = 0)
    int defense;

    @NotNull(message = "Special Attack cannot be null")
    @Min(value = 0)
    int sp_attack;

    @NotNull(message = "Special Defense cannot be null")
    @Min(value = 0)
    int sp_defense;

    @NotNull(message = "Speed cannot be null")
    @Min(value = 0)
    int speed;
}
