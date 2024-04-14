package com.api.poke.usecases;

import com.api.poke.controller.requests.UpdateGymRequestDTO;
import com.api.poke.model.Gym;

import java.util.UUID;

public interface GymUpdater {
    Gym execute(UUID id, UpdateGymRequestDTO requestDTO);
}
