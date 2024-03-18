package com.api.poke.usecases;

import com.api.poke.controller.requests.UpdatePokemonRequestDTO;
import com.api.poke.exceptions.PokemonNotFoundException;
import com.api.poke.model.Pokemon;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.mappers.PokemonEntityMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdatePokemon implements PokemonUpdater {

    PokemonRepository pokemonRepository;
    PokemonEntityMapper pokemonEntityMapper;

    public Pokemon execute(Long id, UpdatePokemonRequestDTO requestDTO) {
        Optional<PokemonEntity> pokemonToUpdate = pokemonRepository.findById(id);

        if (pokemonToUpdate.isEmpty()) {
            throw new PokemonNotFoundException("Pokemon not found for id " + id);
        }

        PokemonEntity pokemonEntity = pokemonToUpdate.get();
        pokemonEntity.setName(requestDTO.getName());
        pokemonEntity.setExperience(requestDTO.getExperience());
        pokemonEntity.setEvolutionLevel(requestDTO.getEvolutionLevel());
        pokemonEntity.setEvolves(requestDTO.isEvolves());
       // pokemonEntity.setImage(requestDTO.getImage());

        return pokemonEntityMapper.toModel(pokemonRepository.save(pokemonEntity));
    }
}
