package com.api.poke.controller.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
//@FieldDefaults(makeFinal = true)
public class TrainerPokemonResponseDTO {
    UUID id;

    PokemonResponseDTO pokemon;

    List<TrainerPokemonMovementResponseDTO> movements;

}