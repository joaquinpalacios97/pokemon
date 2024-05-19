package com.api.poke.repository.mappers;

import com.api.poke.model.Pokemon;
import com.api.poke.model.Trainer;
import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.entities.TrainerEntity;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Component
public class TrainerEntityMapper {
    private final PokemonEntityMapper pokemonEntityMapper;

    public TrainerEntityMapper(PokemonEntityMapper pokemonEntityMapper) {
        this.pokemonEntityMapper = pokemonEntityMapper;
    }


    public TrainerEntity toEntity(Trainer trainer){
        TrainerEntity entity = new TrainerEntity();
        entity.setId(trainer.getId());
        entity.setName(trainer.getName());
        List<PokemonEntity> pokemonEntities = trainer.getPokemons().stream()
                .map(pokemonEntityMapper::toEntity)
                .toList();
        entity.setPokemons(pokemonEntities);
        return entity;
    }

    public Trainer toModel(TrainerEntity trainerEntity){
        return Trainer.builder()
                .id(trainerEntity.getId())
                .name(trainerEntity.getName())
                .pokemons(trainerEntity.getPokemons().stream().map(pokemonEntityMapper::toModel)
                        .collect(Collectors.toList()))
                .build();

    }
}