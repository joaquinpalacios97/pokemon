package com.api.poke.usecases;

import com.api.poke.controller.requests.UpdatePokemonRequestDTO;
import com.api.poke.model.Pokemon;

public interface PokemonUpdater {
    Pokemon execute(Long id,UpdatePokemonRequestDTO requestDTO);
}
