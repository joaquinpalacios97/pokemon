package com.api.poke.controller;

import com.api.poke.controller.presenters.ListMovementPresenter;
import com.api.poke.controller.presenters.MovementPresenter;
import com.api.poke.controller.requests.CreateMovementDTO;
import com.api.poke.controller.requests.UpdateMovementDTO;
import com.api.poke.controller.responses.ListMovementResponseDTO;
import com.api.poke.controller.responses.MovementResponseDTO;
import com.api.poke.model.Movement;
import com.api.poke.service.IMovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movements")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class MovementController {

    IMovementService imovementService;
    ListMovementPresenter listMovementPresenter;
    MovementPresenter movementPresenter;

    @GetMapping("")
    public List<ListMovementResponseDTO> getAll(){
    List<Movement> movements = imovementService.findAll();
    List<ListMovementResponseDTO> listDTO = new ArrayList<>();
    for(Movement movement : movements){
        ListMovementResponseDTO responseDTO = listMovementPresenter.toResponseDTO(movement);
        listDTO.add(responseDTO);
    }
    return listDTO;
    }

    @GetMapping("/{id}")
    public MovementResponseDTO findMovement (@PathVariable UUID id){
        Movement movement = imovementService.findById(id);
        return movementPresenter.toResponse(movement);
    }

    @PostMapping("")
    public ResponseEntity <MovementResponseDTO> create(
            @RequestBody @Validated CreateMovementDTO createMovementDTO
            ){
            Movement createMovement = imovementService.saveMovement(createMovementDTO);
            MovementResponseDTO movementResponseDTO = movementPresenter.toResponse(createMovement);
            return ResponseEntity.status(HttpStatus.CREATED).body(movementResponseDTO);
    }

    @PutMapping("/{id}")
    public MovementResponseDTO update(
            @PathVariable UUID id,
            @RequestBody @Validated UpdateMovementDTO updateMovementDTO
            ){
        Movement updateMovement = imovementService.updateMovement(id, updateMovementDTO);
        return movementPresenter.toResponse(updateMovement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        imovementService.deleteMovement(id);
        return ResponseEntity.noContent().build();
    }

}
