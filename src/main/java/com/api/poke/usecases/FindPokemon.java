package com.api.poke.usecases;

import com.api.poke.exceptions.PokemonNotFoundException;
import com.api.poke.model.Pokemon;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.mappers.PokemonEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FindPokemon implements PokemonFinder {

    private final PokemonRepository pokemonRepository;
    private final PokemonEntityMapper mapper;

    @Override
    public Pokemon findById(Long id) {
        Optional<PokemonEntity> pokemonEntity = pokemonRepository.findById(id);

        if (pokemonEntity.isEmpty()) {
            throw new PokemonNotFoundException("Pokemon not found for id " + id);
        }

        return mapper.toModel(pokemonEntity.get());
    }
}
