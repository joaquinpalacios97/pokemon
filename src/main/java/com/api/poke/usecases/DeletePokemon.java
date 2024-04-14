package com.api.poke.usecases;

import com.api.poke.exceptions.PokemonNotFoundException;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.entities.PokemonEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeletePokemon implements PokemonDeleter {


    PokemonRepository pokemonRepository;

    @Override
    public void deleteById(UUID id) {
        Optional<PokemonEntity> pokemonEntity = pokemonRepository.findById(id);

        if (pokemonEntity.isEmpty()) {
            throw new PokemonNotFoundException("Pokemon not found for id " + id);
        }

        pokemonRepository.deleteById(id);
    }
}
