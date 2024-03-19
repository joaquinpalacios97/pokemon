package com.api.poke.controller;

import com.api.poke.controller.presenters.GymPresenter;
import com.api.poke.controller.presenters.ListGymPresenter;
import com.api.poke.controller.requests.CreateGymRequestDTO;
import com.api.poke.controller.requests.UpdateGymRequestDTO;
import com.api.poke.controller.responses.GymResponseDTO;
import com.api.poke.controller.responses.ListGymResponseDTO;
import com.api.poke.model.Gym;
import com.api.poke.usecases.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/gym")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GymController {

    private final GymLister gymLister;
    private final ListGymPresenter listGymPresenter;
    private final GymFinder gymFinder;
    private final GymPresenter gymPresenter;
    private final GymCreator gymCreator;
    private final GymDeleter gymDeleter;
    private final GymUpdater gymUpdater;
    @GetMapping("")
    public List<ListGymResponseDTO> getAll(){
        List<Gym> gyms = gymLister.execute();
        List<ListGymResponseDTO> listDto = new ArrayList<>();
        for (Gym gym : gyms){
            ListGymResponseDTO responseDTO =  listGymPresenter.toResponse(gym);
        listDto.add(responseDTO);
        }
        return listDto;
    }

    @GetMapping("/{id}")
    public GymResponseDTO findGym(@PathVariable Long id){
        Gym gym = gymFinder.findById(id);
        return gymPresenter.toResponse(gym);
    }

    @PostMapping("")
    public ResponseEntity<GymResponseDTO> create(
        @RequestBody @Validated CreateGymRequestDTO createGymRequestDTO){
        Gym createdGym = gymCreator.execute(createGymRequestDTO);
        GymResponseDTO gymResponseDTO = gymPresenter.toResponse(createdGym);
        return ResponseEntity.status(HttpStatus.CREATED).body(gymResponseDTO);
    }

    @PutMapping("/{id}")
    public GymResponseDTO updateGym(
            @PathVariable Long id, @RequestBody @Validated UpdateGymRequestDTO updateGymRequestDTO)
    {
    Gym updateGym = gymUpdater.execute(id, updateGymRequestDTO);
    GymResponseDTO gymResponseDTO = gymPresenter.toResponse(updateGym);
    return  gymResponseDTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGym(@PathVariable Long id){
        gymDeleter.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}