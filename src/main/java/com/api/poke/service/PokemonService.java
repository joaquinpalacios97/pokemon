package com.api.poke.service;

import com.api.poke.model.Pokemon;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.mappers.PokemonEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PokemonService implements IPokemonService {

    PokemonRepository repository;
    PokemonEntityMapper mapper;

    @Override
    public List<Pokemon> getPokemon() {
        return repository
                .findAll()
                .stream()
                .map(entity -> mapper.toModel(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePokemon(Long id) {
        repository.deleteById(id);

        throw new PokemonNotFoundException(id);
    }


    @Override
    public Pokemon savePokemon(Pokemon poke) {
        return repository.save(poke);
    }

    public Pokemon updatePokemon(Long id, Pokemon pokemon) {
        Pokemon existingPokemon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon not found"));

        existingPokemon.setNombre(pokemon.getNombre());
        existingPokemon.setExperiencia(pokemon.getExperiencia());
        existingPokemon.setNivel_evolucion(pokemon.getNivel_evolucion());
        existingPokemon.setEvoluciona(pokemon.isEvoluciona());
        existingPokemon.setImagen(pokemon.getImagen());

        return repository.save(existingPokemon);
    }

    @Override
    public Pokemon findPoke(Long id) {
        return repository.findById(id).orElse(null);
    }
}