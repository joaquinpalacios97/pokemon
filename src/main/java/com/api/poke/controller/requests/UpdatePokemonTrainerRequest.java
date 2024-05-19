package com.api.poke.controller.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;
@Getter
@AllArgsConstructor
@FieldDefaults (makeFinal = true)
@Builder
public class UpdatePokemonTrainerRequest {

    @NotNull(message = "Old Pokemon ID is required")
    private UUID oldPokemonId;

    @NotNull(message = "New Pokemon ID is required")
    private UUID newPokemonId;
}
