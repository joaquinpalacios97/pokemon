package com.api.poke.service;

import com.api.poke.controller.requests.CreateTrainerRequestDTO;
import com.api.poke.controller.requests.UpdateTrainerRequestDTO;
import com.api.poke.exceptions.TrainerNotFoundException;
import com.api.poke.model.Gym;
import com.api.poke.model.Trainer;
import com.api.poke.repository.TrainerRepository;
import com.api.poke.repository.entities.TrainerEntity;
import com.api.poke.repository.mappers.GymEntityMapper;
import com.api.poke.repository.mappers.TrainerEntityMapper;
import com.api.poke.usecases.GymFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrainerService implements ITrainerService {

    TrainerRepository trainerRepository;
    TrainerEntityMapper trainerEntityMapper;
    GymEntityMapper gymEntityMapper;
    GymFinder gymFinder;

    @Override
    public List<Trainer> findAll() {
        return trainerRepository
                .findAll()
                .stream()
                .map(entity -> trainerEntityMapper.toModel(entity))
                .collect(Collectors.toList());
    }

    public Trainer saveTrainer(CreateTrainerRequestDTO requestDTO) {
        Trainer trainer = Trainer.builder()
                .name(requestDTO.getName())
                .build();
        TrainerEntity trainerEntity = trainerEntityMapper.toEntity(trainer);

        //Gym gym = gymFinder.findById(requestDTO.getGym_id());

        return trainerEntityMapper.toModel(trainerRepository.save(trainerEntity));
    }

    @Override
    public void deleteTrainer(UUID id) {
        Optional<TrainerEntity> trainerEntity = trainerRepository.findById(id);
        if (trainerEntity.isEmpty()) {
            throw new TrainerNotFoundException("Trainer not found for id " + id);
        }
        trainerRepository.deleteById(id);
    }

    @Override
    public Trainer findById(UUID id) {
        Optional<TrainerEntity> trainerEntity = trainerRepository.findById(id);
        if (trainerEntity.isEmpty()) {
            throw new TrainerNotFoundException("Trainer not found for id " + id);
        }
        return trainerEntityMapper.toModel(trainerEntity.get());
    }

    @Override
    public Trainer updateTrainer(UUID id, UpdateTrainerRequestDTO requestDTO) {
        Optional<TrainerEntity> trainerEntity = trainerRepository.findById(id);
        if (trainerEntity.isEmpty()) {
            throw new TrainerNotFoundException("Trainer not found for id " + id);
        }
        TrainerEntity trainer = new TrainerEntity();
        trainer.setName(requestDTO.getName());

        return trainerEntityMapper.toModel(trainerRepository.save(trainer));
    }
}
