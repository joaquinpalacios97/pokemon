package com.api.poke.repository.mappers;

import com.api.poke.model.Trainer;
import com.api.poke.model.TrainerPokemon;
import com.api.poke.model.TrainerPokemonMovement;
import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.entities.TrainerEntity;
import com.api.poke.repository.entities.TrainerPokemonEntity;
import com.api.poke.repository.entities.TrainerPokemonMovementEntity;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Component

public class TrainerEntityMapper {

    private final PokeTrainerEntityMapper pokeTrainerEntityMapper;

    private final MovementEntityMapper movementEntityMapper;

    public TrainerEntity toEntity(Trainer trainer){
        TrainerEntity entity = new TrainerEntity();
        entity.setId(trainer.getId());
        entity.setName(trainer.getName());
        entity.setType(trainer.getType());
        return entity;
    }

    public Trainer toModel(TrainerEntity trainerEntity){
        return Trainer.builder()
                .id(trainerEntity.getId())
                .name(trainerEntity.getName())
                .type(trainerEntity.getType())
                .build();
    }
}
