package com.api.poke.usecases;

import com.api.poke.model.Pokemon;

import java.util.List;

public interface PokemonLister {
    List<Pokemon> execute();
}
