package com.api.poke.controller.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;
@Builder
@Getter
@FieldDefaults(makeFinal = true)
public class TrainerPokemonMovementResponseDTO {
    UUID id;
    MovementResponseDTO movement;
}
