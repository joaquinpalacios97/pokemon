package com.api.poke.usecases;

import com.api.poke.exceptions.GymNotFoundException;
import com.api.poke.repository.GymRepository;
import com.api.poke.repository.entities.GymEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteGym implements GymDeleter{

    GymRepository gymRepository;
    public void deleteById(UUID id) {
        Optional<GymEntity> gymEntity = gymRepository.findById(id);
        if(gymEntity.isEmpty()){
            throw new GymNotFoundException("Gym not found for id " + id);
        }
        gymRepository.deleteById(id);
    }
}
