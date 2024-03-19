package com.api.poke.usecases;

import com.api.poke.model.Gym;
import com.api.poke.repository.GymRepository;
import com.api.poke.repository.mappers.GymEntityMapper;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true)
@AllArgsConstructor
public class ListGym implements GymLister{

    GymRepository gymRepository;
    GymEntityMapper gymEntityMapper;

    public List<Gym> execute() {
        return gymRepository
                .findAll()
                .stream()
                .map(entity ->gymEntityMapper.toModel(entity))
                .collect(Collectors.toList());
    }
}
