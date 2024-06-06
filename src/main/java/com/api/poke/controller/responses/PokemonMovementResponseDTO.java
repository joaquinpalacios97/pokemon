package com.api.poke.controller.responses;

import com.api.poke.model.Attribute;
import com.api.poke.model.PokemonType;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@FieldDefaults(makeFinal = true)
public class PokemonMovementResponseDTO {

    UUID id;

    String name;

    PokemonType type;

    int experience;

    Integer evolutionLevel;

    boolean evolves;

    Attribute attributes;

    List<TrainerPokemonMovementResponseDTO> movements;
}
