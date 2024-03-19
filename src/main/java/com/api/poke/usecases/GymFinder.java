package com.api.poke.usecases;

import com.api.poke.model.Gym;

public interface GymFinder {
    Gym findById(Long id);
}
