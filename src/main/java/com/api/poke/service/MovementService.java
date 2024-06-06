package com.api.poke.service;

import com.api.poke.controller.requests.CreateMovementDTO;
import com.api.poke.controller.requests.UpdateMovementDTO;
import com.api.poke.exceptions.MovementNotFoundException;
import com.api.poke.model.Movement;
import com.api.poke.repository.MovementRepository;
import com.api.poke.repository.entities.MovementEntity;
import com.api.poke.repository.mappers.MovementEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovementService implements IMovementService {

    MovementRepository movementRepository;
    MovementEntityMapper movementEntityMapper;

    @Override
    public List<Movement> findAll() {
        return movementRepository
                .findAll()
                .stream()
                .map(entity -> movementEntityMapper.toModel(entity))
                .collect(Collectors.toList());
    }

    @Override
    public Movement saveMovement(CreateMovementDTO requestDTO) {
        Movement movement = Movement.builder()
                .name(requestDTO.getName())
                .type(requestDTO.getType())
                .power(requestDTO.getPower())
                .accuracy(requestDTO.getAccuracy())
                .pp(requestDTO.getPp())
                .build();

        MovementEntity movementEntity = movementEntityMapper.toEntity(movement);
        return movementEntityMapper.toModel(movementRepository.save(movementEntity));
    }

    @Override
    public Movement updateMovement(UUID id, UpdateMovementDTO requestDTO) {
        Optional<MovementEntity> movementToUpdate = movementRepository.findById(id);
        if(movementToUpdate.isEmpty())
            throw new MovementNotFoundException("Movement not found for id " + id);
        MovementEntity movement =  movementToUpdate.get().toBuilder()
                .name(requestDTO.getName())
               // .type(requestDTO.getType())
                .power(requestDTO.getPower())
                .accuracy(requestDTO.getAccuracy())
                .pp(requestDTO.getPp())
                .build();

        return movementEntityMapper.toModel(movementRepository.save(movement));
    }

    @Override
    public Movement findById(UUID id) {
        Optional<MovementEntity> movementEntity = movementRepository.findById(id);
        if(movementEntity.isEmpty()){
            throw new MovementNotFoundException("Movement not found for id " + id);
        }
        return movementEntityMapper.toModel(movementEntity.get());
    }

    @Override
    public void deleteMovement(UUID id) {
        Optional<MovementEntity> movementEntity = movementRepository.findById(id);
        if(movementEntity.isEmpty()){
            throw new MovementNotFoundException("Movement not found for id " + id);
        }
        movementRepository.deleteById(id);
    }
}
