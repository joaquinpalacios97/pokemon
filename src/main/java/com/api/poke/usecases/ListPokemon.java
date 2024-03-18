package com.api.poke.usecases;

import com.api.poke.model.Pokemon;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.mappers.PokemonEntityMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ListPokemon implements PokemonLister {

    PokemonRepository repository;
    PokemonEntityMapper mapper;

    public List<Pokemon> execute() {
        return repository
                .findAll()
                .stream()
                .map(entity -> mapper.toModel(entity))
                .collect(Collectors.toList());
    }
}
