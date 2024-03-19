package com.api.poke.repository.mappers;

import com.api.poke.model.Gym;
import com.api.poke.repository.entities.GymEntity;
import org.springframework.stereotype.Component;

@Component
public class GymEntityMapper {
    public GymEntity toEntity (Gym gym){
        GymEntity gymEntity = new GymEntity();
        gymEntity.setId(gym.getId());
        gymEntity.setName(gym.getName());
        gymEntity.setType(gym.getType());
        return gymEntity;
    }

    public Gym toModel(GymEntity gymEntity){
        Gym gym = Gym.builder().build();
        gymEntity.getId();
        gymEntity.getName();
        gymEntity.getType();
        return gym;
    }
}
