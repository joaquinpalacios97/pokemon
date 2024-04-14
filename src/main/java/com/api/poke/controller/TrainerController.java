package com.api.poke.controller;

import com.api.poke.controller.presenters.ListTrainerPresenter;
import com.api.poke.controller.presenters.TrainerPresenter;
import com.api.poke.controller.requests.CreateTrainerRequestDTO;
import com.api.poke.controller.requests.UpdateTrainerRequestDTO;
import com.api.poke.controller.responses.GymResponseDTO;
import com.api.poke.controller.responses.ListTrainerResponseDTO;
import com.api.poke.controller.responses.TrainerResponseDTO;
import com.api.poke.model.Trainer;
import com.api.poke.service.ITrainerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trainer")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class TrainerController {

    ITrainerService iTrainerService;
    ListTrainerPresenter listTrainerPresenter;
    TrainerPresenter trainerPresenter;

    @GetMapping("")
    public List<ListTrainerResponseDTO> getAll() {
        List<Trainer> trainers = iTrainerService.findAll();
        List<ListTrainerResponseDTO> listDTO = new ArrayList<>();
        for (Trainer trainer : trainers) {
            ListTrainerResponseDTO responseDTO = listTrainerPresenter.toResponse(trainer);
            listDTO.add(responseDTO);
        }
        return listDTO;
    }

    @GetMapping("/{id}")
    public TrainerResponseDTO findTrainer(@PathVariable UUID id) {
        Trainer trainer = iTrainerService.findById(id);
        return trainerPresenter.toResponse(trainer);
    }

    @PostMapping("")
    public ResponseEntity<TrainerResponseDTO> create(
            @RequestBody @Validated CreateTrainerRequestDTO createTrainerRequestDTO

    ) {
        Trainer createdTrainer = iTrainerService.saveTrainer(createTrainerRequestDTO);
        TrainerResponseDTO trainerResponseDTO = trainerPresenter.toResponse(createdTrainer);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainerResponseDTO);
    }

    @PutMapping("/{id}")
    public TrainerResponseDTO update(
            @PathVariable UUID id, @RequestBody @Validated UpdateTrainerRequestDTO updateTrainerRequestDTO
    ) {
        Trainer updateTrainer = iTrainerService.updateTrainer(id, updateTrainerRequestDTO);
        TrainerResponseDTO responseDTO = trainerPresenter.toResponse(updateTrainer);
        return responseDTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        iTrainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }
}
