package com.api.poke.usecases;

import com.api.poke.controller.requests.UpdatePokemonRequestDTO;
import com.api.poke.model.Pokemon;

import java.util.UUID;

public interface PokemonUpdater {
    Pokemon execute(UUID id, UpdatePokemonRequestDTO requestDTO);
}
