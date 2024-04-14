package com.api.poke.repository.mappers;

import com.api.poke.model.Gym;
import com.api.poke.repository.entities.GymEntity;
import com.api.poke.repository.entities.TrainerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GymEntityMapper {

    private final TrainerEntityMapper trainerEntityMapper;

    public GymEntityMapper(TrainerEntityMapper trainerEntityMapper) {
        this.trainerEntityMapper = trainerEntityMapper;
    }

    public GymEntity toEntity (Gym gym){
        GymEntity gymEntity = new GymEntity();
        gymEntity.setId(gym.getId());
        gymEntity.setName(gym.getName());
        gymEntity.setType(gym.getType());
        List<TrainerEntity> trainerEntities = gym.getTrainers().stream()
                .map(trainerEntityMapper::toEntity)
                .collect(Collectors.toList());
        gymEntity.setTrainers(trainerEntities);
        return gymEntity;
    }

    public Gym toModel(GymEntity gymEntity){
        return Gym.builder()
                .id(gymEntity.getId())
                .name(gymEntity.getName())
                .type(gymEntity.getType())
                .trainers(gymEntity.getTrainers().stream()
                        .map(trainerEntityMapper::toModel)
                        .collect(Collectors.toList()))
                .build();
    }
}
