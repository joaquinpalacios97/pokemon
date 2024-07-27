package com.api.poke.controller;

import com.api.poke.controller.presenters.ListPokemonPresenter;
import com.api.poke.controller.presenters.PokemonPresenter;
import com.api.poke.controller.requests.CreateAttributeDTO;
import com.api.poke.controller.requests.CreatePokemonRequestDTO;
import com.api.poke.controller.requests.UpdatePokemonRequestDTO;
import com.api.poke.controller.responses.ListPokemonResponseDTO;
import com.api.poke.controller.responses.PokemonResponseDTO;
import com.api.poke.model.Pokemon;
import com.api.poke.usecases.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/pokemons")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class PokemonController {

    private final PokemonPresenter presenter;
    private final ListPokemonPresenter listPresenter;
    private final PokemonCreator pokemonCreator;
    private final PokemonFinder pokemonFinder;
    private final PokemonLister pokemonLister;
    private final PokemonUpdater pokemonUpdater;
    private final PokemonDeleter pokemonDeleter;


    @GetMapping("")
    public List<ListPokemonResponseDTO> getAll() {
        List<Pokemon> pokemons = pokemonLister.execute();
        List<ListPokemonResponseDTO> results = new ArrayList<>();
        for (Pokemon pokemon : pokemons) {
            results.add(listPresenter.toResponse(pokemon));
        }
        return results;
    }

    @GetMapping("/{id}")
    public PokemonResponseDTO findPokemon(@PathVariable UUID id) {
        return presenter.toResponse(pokemonFinder.findById(id));
    }

   @PostMapping("")
    public ResponseEntity<PokemonResponseDTO> createPokemon(
            @RequestBody @Validated CreatePokemonRequestDTO pokemonRequestDTO) {
       Pokemon createdPokemon = pokemonCreator.execute(pokemonRequestDTO);
       PokemonResponseDTO responseDTO = presenter.toResponse(createdPokemon);
       return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @PutMapping("/{id}")
    public PokemonResponseDTO updatePokemon(
            @PathVariable UUID id,
            @RequestBody @Validated UpdatePokemonRequestDTO updateRequestDTO) {
        Pokemon updatedPokemon = pokemonUpdater.execute(id, updateRequestDTO);
        return presenter.toResponse(updatedPokemon);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable UUID id) {
        pokemonDeleter.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}