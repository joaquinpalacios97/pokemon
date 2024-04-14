package com.api.poke.usecases;

import java.util.UUID;

public interface PokemonDeleter {
    void deleteById(UUID id);
}
