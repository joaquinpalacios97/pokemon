package com.api.poke.controller.responses;

import com.api.poke.model.Attribute;
import com.api.poke.model.PokemonType;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Builder
@FieldDefaults(makeFinal = true)
@Getter
public class PokemonResponseDTO {

    UUID id;
    String name;
    PokemonType type;
    int experience;
    Integer evolutionLevel;
    boolean evolves;
    Attribute attributes;
    String imageBase64;
}