package com.api.poke.controller;

import com.api.poke.controller.presenters.ListTrainerPresenter;
import com.api.poke.controller.presenters.TrainerPresenter;
import com.api.poke.controller.requests.CreateTrainerRequestDTO;
import com.api.poke.controller.requests.CreateTrainerPokemonsRequest;
import com.api.poke.controller.requests.UpdatePokemonTrainerRequest;
import com.api.poke.controller.requests.UpdateTrainerRequestDTO;
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
@RequestMapping("/trainers")
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
        return trainerPresenter.toResponse(updateTrainer);
    }

    @PutMapping("/{id}/pokemons")
    public ResponseEntity<TrainerResponseDTO> updatePokemonList(
            @PathVariable UUID id,
            @RequestBody @Validated CreateTrainerPokemonsRequest updatePokemonListRequestDTO
    ) {
        Trainer updatedTrainer = iTrainerService.updateTrainerPokemons(id, updatePokemonListRequestDTO);
        TrainerResponseDTO trainerResponseDTO = trainerPresenter.toResponse(updatedTrainer);
        return ResponseEntity.ok(trainerResponseDTO);
    }

    @PutMapping("/{id}/pokemons/update")
    public ResponseEntity<TrainerResponseDTO> updateTrainerPokemon(
            @PathVariable UUID id,
            @RequestBody @Validated UpdatePokemonTrainerRequest requestDTO
    ) {
        Trainer updatedTrainer = iTrainerService.updateTrainerPokemon(id, requestDTO);
        TrainerResponseDTO trainerResponseDTO = trainerPresenter.toResponse(updatedTrainer);
        return ResponseEntity.ok(trainerResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        iTrainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }
}
