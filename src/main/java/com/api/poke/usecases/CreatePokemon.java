package com.api.poke.usecases;

import com.api.poke.controller.requests.CreatePokemonRequestDTO;
import com.api.poke.model.Pokemon;
import com.api.poke.repository.PokemonRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreatePokemon {

    PokemonRepository pokemonRepository;

    public Pokemon execute(CreatePokemonRequestDTO requestDTO) {
        // creo pokeomon
        // guardo db yt devuelvo

//        return pokemonRepository.save(null);

        return null;
    }
}
