package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.ListTrainerResponseDTO;
import com.api.poke.model.Trainer;
import org.springframework.stereotype.Component;

@Component
public class ListTrainerPresenter {
    public ListTrainerResponseDTO toResponse(Trainer trainer){
        return ListTrainerResponseDTO
                .builder()
                .name(trainer.getName())
                .pokemons(trainer.getPokemons())
                .build();
    }
}
