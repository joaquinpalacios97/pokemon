package com.api.poke.usecases;

import com.api.poke.exceptions.GymNotFoundException;
import com.api.poke.model.Gym;
import com.api.poke.repository.GymRepository;
import com.api.poke.repository.entities.GymEntity;
import com.api.poke.repository.mappers.GymEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor

public class FindGym implements GymFinder{

    GymRepository gymRepository;
    GymEntityMapper gymEntityMapper;
    public Gym findById(Long id) {
        Optional<GymEntity> gymEntity = gymRepository.findById(id);
        if(gymEntity.isEmpty()){
            throw new GymNotFoundException("Gym not found for id " + id);
        }
        return gymEntityMapper.toModel(gymEntity.get());
    }
}
