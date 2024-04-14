package com.api.poke.usecases;

import java.util.UUID;

public interface GymDeleter {
    void deleteById(UUID id);
}
