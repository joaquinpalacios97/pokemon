package com.api.poke.usecases;

import com.api.poke.controller.requests.UpdateGymRequestDTO;
import com.api.poke.model.Gym;

public interface GymUpdater {
    Gym execute(Long id, UpdateGymRequestDTO requestDTO);
}
