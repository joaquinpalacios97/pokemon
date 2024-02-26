package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.PokemonResponseDTO;
import com.api.poke.model.Pokemon;
import org.springframework.stereotype.Component;

@Component
public class PokemonPresenter {

    public PokemonResponseDTO toResponse(Pokemon pokemon) {
        return PokemonResponseDTO
                .builder()
                .id(pokemon.getId_pokemon())
                .name(pokemon.getNombre())
                .build();
    }
}
