package com.api.poke.usecases;

import com.api.poke.controller.requests.CreatePokemonRequestDTO;
import com.api.poke.model.Pokemon;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.mappers.PokemonEntityMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreatePokemon implements PokemonCreator{

    PokemonRepository pokemonRepository;
    PokemonEntityMapper pokemonEntityMapper;

    public Pokemon execute(CreatePokemonRequestDTO requestDTO) {
        Pokemon pokemon = new Pokemon.Builder()
                .name(requestDTO.getName())
                .experience(requestDTO.getExperience())
                .evolutionLevel(requestDTO.getEvolutionLevel())
                .evolves(requestDTO.getEvolves())
                .build();

        PokemonEntity pokemonEntity = pokemonEntityMapper.toEntity(pokemon);

        return pokemonEntityMapper.toModel(pokemonRepository.save(pokemonEntity));
    }
}