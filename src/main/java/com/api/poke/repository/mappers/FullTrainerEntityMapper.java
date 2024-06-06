package com.api.poke.repository.mappers;

import com.api.poke.model.*;
import com.api.poke.repository.entities.TrainerEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class FullTrainerEntityMapper {

    public Trainer toModel(TrainerEntity trainerEntity) {
        Trainer model = Trainer.builder()
                .id(trainerEntity.getId())
                .name(trainerEntity.getName())
                .build();

        model.setPokeTrainers(
                trainerEntity.getPokeTrainers().stream()
                        .map(trainerPokemonEntity -> TrainerPokemon.builder()
                                .id(trainerPokemonEntity.getId())
                                .pokemon(Pokemon.builder()
                                        .id(trainerPokemonEntity.getPokemon().getId())
                                        .name(trainerPokemonEntity.getPokemon().getName())
                                                .type(trainerPokemonEntity.getPokemon().getType())
                                                .experience(trainerPokemonEntity.getPokemon().getExperience())
                                                .evolutionLevel(trainerPokemonEntity.getPokemon().getEvolutionLevel())
                                                .evolves(trainerPokemonEntity.getPokemon().isEvolves())
                                        .attributes(Attribute.builder()
                                                .id(trainerPokemonEntity.getPokemon().getAttributes().getId())
                                                .hp(trainerPokemonEntity.getPokemon().getAttributes().getHp())
                                                .attack(trainerPokemonEntity.getPokemon().getAttributes().getAttack())
                                                .defense(trainerPokemonEntity.getPokemon().getAttributes().getDefense())
                                                .sp_attack(trainerPokemonEntity.getPokemon().getAttributes().getSp_attack())
                                                .sp_defense(trainerPokemonEntity.getPokemon().getAttributes().getSp_defense())
                                                .speed(trainerPokemonEntity.getPokemon().getAttributes().getSpeed())
                                                .build())
                                        .build())
                                .trainer(model)
                                .trainerPokemonMovements(trainerPokemonEntity.getTrainerPokemonMovements().stream()
                                        .map(trainerPokemonMovementEntity -> TrainerPokemonMovement.builder()
                                                .id(trainerPokemonMovementEntity.getId())
                                                .movement(Movement.builder()
                                                        .id(trainerPokemonMovementEntity.getMovement().getId())
                                                        .name(trainerPokemonMovementEntity.getMovement().getName())
                                                        .type(trainerPokemonMovementEntity.getMovement().getType())
                                                        .power(trainerPokemonMovementEntity.getMovement().getPower())
                                                        .accuracy(trainerPokemonMovementEntity.getMovement().getAccuracy())
                                                        .pp(trainerPokemonMovementEntity.getMovement().getPp())
                                                        .build())
                                                .trainerPokemon(TrainerPokemon.builder().id(trainerPokemonMovementEntity.getTrainerPokemon().getId()).build())
                                                .build())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList())
        );

        return model;
    }
}
