package com.api.poke.controller.responses;


import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@FieldDefaults(makeFinal = true)
public class TrainerResponseDTO {
    String name;
    List<TrainerPokemonResponseDTO> pokeTrainers;
}
