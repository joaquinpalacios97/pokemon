package com.api.poke.controller;

import com.api.poke.controller.presenters.PokemonPresenter;
import com.api.poke.controller.requests.CreatePokemonRequestDTO;
import com.api.poke.controller.responses.PokemonResponseDTO;
import com.api.poke.model.Pokemon;
import com.api.poke.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;
    private final PokemonPresenter presenter;

    @GetMapping("/pokemons")
    public List<Pokemon> getPokemon() {
        List<Pokemon> pokemons = pokemonService.getPokemon();
        // Convertir las imágenes a representación en base64
        for (Pokemon pokemon : pokemons) {
            pokemon.setImagenBase64(convertirImagenABase64(pokemon.getImagen()));
        }
        return pokemons;
    }

    private String convertirImagenABase64(byte[] imagen) {
        return Base64.getEncoder().encodeToString(imagen);
    }

    @PostMapping("/pokemons")
    public PokemonResponseDTO createPokemon(
            @RequestBody @Validated CreatePokemonRequestDTO pokemonRequestDTO
    ) {
        Pokemon createdPokemon = pokemonService.createPokemon(pokemonRequestDTO);

        return HttpStatus.CREATED(presenter.toResponse(createdPokemon));

        try {
            Pokemon poke = new Pokemon();
            poke.setNombre(nombre);
            poke.setExperiencia(experiencia);
            poke.setNivel_evolucion(nivelEvolucion);
            poke.setEvoluciona(evoluciona);
            poke.setImagen(imagen.getBytes());

            Pokemon savedPokemon = pokemonService.savePokemon(poke);
            return new ResponseEntity<>(savedPokemon, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping("/pokemon/{id}")
    public ResponseEntity<Pokemon> findPokemon(@PathVariable Long id) {
        try {
            Pokemon poke = pokemonService.findPoke(id);
            if (id != null) {
                return new ResponseEntity<>(poke, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/pokemon/editar/{id}")
    public ResponseEntity<Pokemon> editarPokemon(@PathVariable Long id, @RequestBody Pokemon pokemonActualizado) {
        try {
            Pokemon pokemonExistente = pokemonService.findPoke(id);
            if (pokemonExistente != null) {
                pokemonExistente.setNombre(pokemonActualizado.getNombre());
                pokemonExistente.setExperiencia(pokemonActualizado.getExperiencia());
                pokemonExistente.setNivel_evolucion(pokemonActualizado.getNivel_evolucion());
                pokemonExistente.setEvoluciona(pokemonActualizado.isEvoluciona());
                pokemonExistente.setImagen(pokemonActualizado.getImagen());

                Pokemon pokemonGuardado = pokemonService.savePokemon(pokemonExistente);

                return new ResponseEntity<>(pokemonGuardado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/pokemon/eliminar/{id}")
    public ResponseEntity<Map<String, String>> deletePokemon(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "El Pokémon ha sido eliminado correctamente.");
        return ResponseEntity.ok().body(response);
    }
}