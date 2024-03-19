package com.api.poke.usecases;

import com.api.poke.model.Gym;

import java.util.List;

public interface GymLister {
    List<Gym> execute();
}
