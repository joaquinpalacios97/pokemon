package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.PokemonResponseDTO;
import com.api.poke.model.Pokemon;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class PokemonPresenter {
    public PokemonResponseDTO toResponse(Pokemon pokemon) {
        return PokemonResponseDTO
                .builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .type(pokemon.getType())
                .experience(pokemon.getExperience())
                .evolutionLevel(pokemon.getEvolutionLevel())
                .evolves(pokemon.isEvolves())
                .attributes(pokemon.getAttributes())
                .imageBase64(pokemon.getImageBase64())
                .build();
    }
}