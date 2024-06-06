package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.TrainerPokemonMovementResponseDTO;
import com.api.poke.model.TrainerPokemonMovement;
import org.springframework.stereotype.Component;

@Component
public class TrainerPokemonMovementPresenter {
    public TrainerPokemonMovementResponseDTO toResponse(TrainerPokemonMovement trainerPokemonMovement) {
        return TrainerPokemonMovementResponseDTO.builder()
                .build();
    }
}
