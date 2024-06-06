package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.*;
import com.api.poke.model.Movement;
import com.api.poke.model.Trainer;
import com.api.poke.model.TrainerPokemon;
import com.api.poke.model.TrainerPokemonMovement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Component
public class TrainerPresenter {

    private final PokemonPresenter pokemonPresenter;
    private final TrainerPokemonMovementPresenter trainerPokemonMovementPresenter;
    private MovementPresenter movementPresenter;

    public TrainerResponseDTO toResponse(Trainer trainer) {
        List<TrainerPokemonResponseDTO> pokeTrainers = trainer.getPokeTrainers().stream()
                .map(pokeTrainer -> TrainerPokemonResponseDTO.builder()
                        .id(pokeTrainer.getId())
                        .pokemon(pokemonPresenter.toResponse(pokeTrainer.getPokemon()))
                        .movements(pokeTrainer.getTrainerPokemonMovements().stream()
                                .map(trainerPokemonMovement -> TrainerPokemonMovementResponseDTO.builder()
                                        .id(trainerPokemonMovement.getId())
                                        .movement(movementPresenter.toResponse(trainerPokemonMovement.getMovement()))
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return TrainerResponseDTO.builder()
                .name(trainer.getName())
                .pokeTrainers(pokeTrainers)
                .build();
    }
}