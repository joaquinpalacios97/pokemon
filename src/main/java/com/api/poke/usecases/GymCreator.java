package com.api.poke.usecases;

import com.api.poke.controller.requests.CreateGymRequestDTO;
import com.api.poke.model.Gym;

public interface GymCreator {
    Gym execute(CreateGymRequestDTO requestDTO);
}
