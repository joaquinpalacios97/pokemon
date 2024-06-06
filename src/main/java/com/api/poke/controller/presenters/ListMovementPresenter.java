package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.ListMovementResponseDTO;
import com.api.poke.model.Movement;
import org.springframework.stereotype.Component;

@Component
public class ListMovementPresenter {
    public ListMovementResponseDTO toResponseDTO(Movement movement){
        return ListMovementResponseDTO.builder()
                .id(movement.getId())
                .name(movement.getName())
                .type(movement.getType())
                .power(movement.getPower())
                .accuracy(movement.getAccuracy())
                .pp(movement.getPp())
                .build();
    }
}
