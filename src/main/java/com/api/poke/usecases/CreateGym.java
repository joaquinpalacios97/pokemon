package com.api.poke.usecases;

import com.api.poke.controller.requests.CreateGymRequestDTO;
import com.api.poke.model.Gym;
import com.api.poke.repository.GymRepository;
import com.api.poke.repository.entities.GymEntity;
import com.api.poke.repository.mappers.GymEntityMapper;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true)
@AllArgsConstructor
public class CreateGym implements GymCreator{

    GymRepository gymRepository;
    GymEntityMapper gymEntityMapper;

    public Gym execute(CreateGymRequestDTO requestDTO) {
        Gym gym = Gym.builder()
                .name(requestDTO.getName())
                .type(requestDTO.getType())
                .build();

        GymEntity gymEntity = gymEntityMapper.toEntity(gym);

        return gymEntityMapper.toModel(gymRepository.save(gymEntity));
    }
}
