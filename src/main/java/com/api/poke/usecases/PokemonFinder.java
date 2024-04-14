package com.api.poke.usecases;

import com.api.poke.model.Pokemon;

import java.util.UUID;

public interface PokemonFinder {
    Pokemon findById(UUID id);
}
