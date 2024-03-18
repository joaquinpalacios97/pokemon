package com.api.poke.usecases;

import com.api.poke.model.Pokemon;

public interface PokemonFinder {
    Pokemon findById(Long id);
}
