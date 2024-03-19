package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.GymResponseDTO;
import com.api.poke.model.Gym;
import org.springframework.stereotype.Component;

@Component

public class GymPresenter {
    public GymResponseDTO toResponse(Gym gym){
        return GymResponseDTO
                .builder()
                .name(gym.getName())
                .type(gym.getType())
                .build();
    }
}