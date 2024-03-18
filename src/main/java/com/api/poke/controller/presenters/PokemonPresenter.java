package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.PokemonResponseDTO;
import com.api.poke.model.Pokemon;
import org.springframework.stereotype.Component;

@Component
public class PokemonPresenter {
//ESTO SE ARMA DEL/ EN BASE AL MODELO .     DE ENTIDAD PASO A MODELO PERO NO ACÁ
    public PokemonResponseDTO toResponse(Pokemon pokemon) {
        return PokemonResponseDTO
                .builder()
                .id(String.valueOf(pokemon.getId_pokemon()))
                .name(pokemon.getName())
                .build();
    }
}