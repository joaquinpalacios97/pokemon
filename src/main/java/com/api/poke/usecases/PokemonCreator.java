package com.api.poke.usecases;

import com.api.poke.controller.requests.CreatePokemonRequestDTO;
import com.api.poke.model.Pokemon;

public interface PokemonCreator {
    Pokemon execute(CreatePokemonRequestDTO requestDTO);
}