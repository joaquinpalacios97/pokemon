package com.api.poke.controller.responses;

import com.api.poke.model.Pokemon;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Builder
@FieldDefaults(makeFinal = true)
@Getter
public class PokemonTrainerResponseDTO {
    private List<Pokemon> pokemonIds;
}
