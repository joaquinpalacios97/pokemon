package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.PokemonResponseDTO;
import com.api.poke.model.Pokemon;
import org.springframework.stereotype.Component;

@Component
public class PokemonPresenter {
//ESTO SE ARMA DEL/ EN BASE AL MODELO .
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
                .build();
    }
}