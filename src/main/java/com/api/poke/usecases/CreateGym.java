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
        // Convertir los entrenadores del DTO a entidades
        List<TrainerEntity> trainerEntities = requestDTO.getTrainers()
                .stream()
                .map(trainerEntityMapper::toEntity)
                .collect(Collectors.toList());

        // Crear una instancia de GymEntity
        GymEntity gymEntity = new GymEntity();
        gymEntity.setName(requestDTO.getName());
        gymEntity.setType(requestDTO.getType());
        gymEntity.setTrainers(trainerEntities); // Asignar los entrenadores al gimnasio

        // Guardar el gimnasio en la base de datos
        gymEntity = gymRepository.save(gymEntity);

        // Convertir GymEntity a Gym y devolverlo
        return gymEntityMapper.toModel(gymEntity);

    }
}