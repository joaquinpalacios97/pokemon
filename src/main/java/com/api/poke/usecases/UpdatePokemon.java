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
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdatePokemon implements PokemonUpdater {

    PokemonRepository pokemonRepository;
    PokemonEntityMapper pokemonEntityMapper;

    public Pokemon execute(UUID id, UpdatePokemonRequestDTO requestDTO) {
        Optional<PokemonEntity> pokemonToUpdate = pokemonRepository.findById(id);

        if (pokemonToUpdate.isEmpty()) {
            throw new PokemonNotFoundException("Pokemon not found for id " + id);
        }
//TODO: VER QUE ONDA
        PokemonEntity pokemonEntity = pokemonToUpdate.get().builder()
                .name(requestDTO.getName())
                .experience(requestDTO.getExperience())
                .evolutionLevel(requestDTO.getEvolutionLevel())
                .evolves(requestDTO.isEvolves())
                .build();

       // pokemonEntity.setImage(requestDTO.getImage());

        return pokemonEntityMapper.toModel(pokemonRepository.save(pokemonEntity));
    }
}
