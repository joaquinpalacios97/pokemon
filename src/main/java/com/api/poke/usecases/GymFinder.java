package com.api.poke.usecases;

import com.api.poke.model.Gym;

import java.util.UUID;

public interface GymFinder {
    Gym findById(UUID id);
}
