package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.ListPokemonResponseDTO;
import com.api.poke.model.Pokemon;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ListPokemonPresenter {
    public ListPokemonResponseDTO toResponse(Pokemon pokemon){
        return ListPokemonResponseDTO
                .builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .type(pokemon.getType())
                .experience(pokemon.getExperience())
                .evolutionLevel(pokemon.getEvolutionLevel())
                .evolves(pokemon.isEvolves())
                .imageBase64(pokemon.getImageBase64())
          //      .image(pokemon.getImage())
          //      .imageBase64(convertirImagenABase64(pokemon.getImage()))
                .attributes(pokemon.getAttributes())
                .build();
    }
}
