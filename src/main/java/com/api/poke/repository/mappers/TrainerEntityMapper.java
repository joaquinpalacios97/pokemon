package com.api.poke.repository.mappers;

import com.api.poke.model.Trainer;
import com.api.poke.repository.entities.TrainerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainerEntityMapper {



    public TrainerEntity toEntity(Trainer trainer){
        TrainerEntity entity = new TrainerEntity();
        entity.setId(trainer.getId());
        entity.setName(trainer.getName());
        return entity;
    }

    public Trainer toModel(TrainerEntity trainerEntity){
        return Trainer.builder()
                .id(trainerEntity.getId())
                .name(trainerEntity.getName())
                .build();
    }
}