package com.api.poke.service;

import com.api.poke.controller.requests.CreateMovementDTO;
import com.api.poke.controller.requests.UpdateMovementDTO;
import com.api.poke.model.Movement;

import java.util.List;
import java.util.UUID;

public interface IMovementService {
    List<Movement> findAll();

    Movement saveMovement (CreateMovementDTO requestDTO);

    Movement updateMovement(UUID id, UpdateMovementDTO requestDTO);
    Movement findById (UUID id);
    void deleteMovement(UUID id);
}
