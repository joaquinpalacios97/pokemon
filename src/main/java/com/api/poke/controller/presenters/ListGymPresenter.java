package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.ListGymResponseDTO;
import com.api.poke.model.Gym;
import org.springframework.stereotype.Component;

@Component
public class ListGymPresenter {
    public ListGymResponseDTO toResponse(Gym gym) {
        return ListGymResponseDTO
                .builder()
                .name(gym.getName())
                .type(gym.getType())
                .trainers(gym.getTrainers())
                .build();
    }
}
