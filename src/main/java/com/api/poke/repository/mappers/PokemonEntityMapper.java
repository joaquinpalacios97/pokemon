package com.api.poke.repository.mappers;

import com.api.poke.model.Pokemon;
import com.api.poke.repository.entities.PokemonEntity;
import org.springframework.stereotype.Component;

@Component
public class PokemonEntityMapper {

    public PokemonEntity toEntity(Pokemon pokemon) {}

    public Pokemon toModel(PokemonEntity entity) {}
}
