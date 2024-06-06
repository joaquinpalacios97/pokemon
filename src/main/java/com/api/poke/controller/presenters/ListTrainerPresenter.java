package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.ListTrainerResponseDTO;
import com.api.poke.controller.responses.TrainerResponseDTO;
import com.api.poke.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListTrainerPresenter {

    @Autowired
    private TrainerPresenter trainerPresenter;

    public ListTrainerResponseDTO toResponse(List<Trainer> trainers) {
        List<TrainerResponseDTO> trainerResponseDTOs = trainers.stream()
                .map(trainerPresenter::toResponse)
                .collect(Collectors.toList());
        return ListTrainerResponseDTO.builder()
                .trainers(trainerResponseDTOs)
                .build();
    }
}
