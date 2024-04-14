package com.api.poke.controller;

import com.api.poke.controller.presenters.ListPokemonPresenter;
import com.api.poke.controller.presenters.PokemonPresenter;
import com.api.poke.controller.requests.CreatePokemonRequestDTO;
import com.api.poke.controller.requests.UpdatePokemonRequestDTO;
import com.api.poke.controller.responses.ListPokemonResponseDTO;
import com.api.poke.controller.responses.PokemonResponseDTO;
import com.api.poke.model.Pokemon;
import com.api.poke.usecases.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            ListPokemonResponseDTO response = listPresenter.toResponse(pokemon);
            results.add(response);
        }
        return results;
    }

    @GetMapping("/{id}")
    public PokemonResponseDTO findPokemon(@PathVariable UUID id) {
        Pokemon pokemon = pokemonFinder.findById(id);

        return presenter.toResponse(pokemon);
    }

    @PostMapping("")
    public ResponseEntity<PokemonResponseDTO> createPokemon(
            @RequestBody @Validated CreatePokemonRequestDTO pokemonRequestDTO) {
        //crear una variable con el formato del pokemon(modelo)
        //y agarra los datos ingresados en pokemonRequestDTO
        Pokemon createdPokemon = pokemonCreator.execute(pokemonRequestDTO);
        // crea una variable con el formato que se muestra de respuesta front
        // y los datos se arman en base al builder con los datos que vienen
        // del front
        PokemonResponseDTO responseDTO = presenter.toResponse(createdPokemon);
        //devuelve el estado con los datos armados en PokemonResponseDTO
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public PokemonResponseDTO updatePokemon(
            @PathVariable UUID id,
            @RequestBody @Validated UpdatePokemonRequestDTO updateRequestDTO) {
        // Ejecuta la actualización del Pokémon
        Pokemon updatedPokemon = pokemonUpdater.execute(id, updateRequestDTO);
        // Convierte el Pokémon actualizado en una respuesta para el cliente
        PokemonResponseDTO responseDTO = presenter.toResponse(updatedPokemon);
        return responseDTO;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable UUID id) {
        pokemonDeleter.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}