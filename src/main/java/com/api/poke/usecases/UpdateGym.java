package com.api.poke.usecases;

import com.api.poke.controller.requests.UpdateGymRequestDTO;
import com.api.poke.exceptions.GymNotFoundException;
import com.api.poke.exceptions.TrainerNotFoundException;
import com.api.poke.model.Gym;
import com.api.poke.model.Trainer;
import com.api.poke.repository.GymRepository;
import com.api.poke.repository.TrainerRepository;
import com.api.poke.repository.entities.GymEntity;
import com.api.poke.repository.entities.TrainerEntity;
import com.api.poke.repository.mappers.GymEntityMapper;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class UpdateGym implements GymUpdater {

    GymRepository gymRepository;
    GymEntityMapper gymEntityMapper;
    TrainerRepository trainerRepository;

    public Gym execute(UUID id, UpdateGymRequestDTO requestDTO) {
        Optional<GymEntity> gymEntityOptional = gymRepository.findById(id);

        if (gymEntityOptional.isEmpty()){
            throw new GymNotFoundException("Gym not found for id " + id);
        }

        GymEntity gym =  gymEntityOptional.get();
        gym.setName(requestDTO.getName());
        gym.setType(requestDTO.getType());

        List<UUID> trainersIds = requestDTO.getTrainersIds();

        return gymEntityMapper.toModel(gymRepository.save(gym));
    }
}
