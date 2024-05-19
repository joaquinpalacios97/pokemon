package com.api.poke.usecases;

import com.api.poke.controller.requests.CreateGymRequestDTO;
import com.api.poke.model.Gym;
import com.api.poke.repository.GymRepository;
import com.api.poke.repository.entities.GymEntity;
import com.api.poke.repository.entities.TrainerEntity;
import com.api.poke.repository.mappers.GymEntityMapper;
import com.api.poke.repository.mappers.TrainerEntityMapper;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true)
@AllArgsConstructor
public class CreateGym implements GymCreator{

    GymRepository gymRepository;
    GymEntityMapper gymEntityMapper;
    TrainerEntityMapper trainerEntityMapper;

    public Gym execute(CreateGymRequestDTO requestDTO) {

        List<TrainerEntity> trainerEntities = requestDTO.getTrainers()
                .stream()
                .map(trainerEntityMapper::toEntity)
                .collect(Collectors.toList());


        GymEntity gymEntity = GymEntity.builder()
                .name(requestDTO.getName())
                .type(requestDTO.getType())
                .trainers(trainerEntities)
                .build();


        gymEntity = gymRepository.save(gymEntity);


        return gymEntityMapper.toModel(gymEntity);

    }
}