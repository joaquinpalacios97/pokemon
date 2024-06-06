package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.MovementResponseDTO;
import com.api.poke.model.Movement;
import org.springframework.stereotype.Component;

@Component
public class MovementPresenter {
    public MovementResponseDTO toResponse(Movement movement){
        return MovementResponseDTO.builder()
                .id(movement.getId())
                .name(movement.getName())
                .type(movement.getType())
                .power(movement.getPower())
                .accuracy(movement.getAccuracy())
                .pp(movement.getPp())
                .build();
    }
}
