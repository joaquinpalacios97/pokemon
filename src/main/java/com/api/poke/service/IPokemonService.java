package com.api.poke.service;

import com.api.poke.model.Pokemon;
import java.util.List;

public interface IPokemonService {
     List<Pokemon> getPokemon();
     void deletePokemon(Long id);
     Pokemon savePokemon(Pokemon poke);

     Pokemon findPoke(Long id);
}
