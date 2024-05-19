package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.TrainerResponseDTO;
import com.api.poke.model.Trainer;
import org.springframework.stereotype.Component;

@Component
public class TrainerPresenter {
    public TrainerResponseDTO toResponse(Trainer trainer){
        return TrainerResponseDTO
                .builder()
                .name(trainer.getName())
                .pokemons(trainer.getPokemons())
                .build();
    }
}
