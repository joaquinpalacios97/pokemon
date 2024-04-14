package com.api.poke.usecases;

import com.api.poke.controller.requests.UpdateGymRequestDTO;
import com.api.poke.exceptions.GymNotFoundException;
import com.api.poke.model.Gym;
import com.api.poke.repository.GymRepository;
import com.api.poke.repository.entities.GymEntity;
import com.api.poke.repository.mappers.GymEntityMapper;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class UpdateGym implements GymUpdater {

    GymRepository gymRepository;
    GymEntityMapper gymEntityMapper;
    public Gym execute(UUID id, UpdateGymRequestDTO requestDTO) {
        Optional<GymEntity> gymEntity = gymRepository.findById(id);
        if (gymEntity.isEmpty()){
            throw new GymNotFoundException("Gym not foun for id " + id);
        }
        GymEntity gym = new GymEntity();
        gym.setName(requestDTO.getName());
        gym.setType(requestDTO.getType());
        return gymEntityMapper.toModel(gymRepository.save(gym));
    }
}
