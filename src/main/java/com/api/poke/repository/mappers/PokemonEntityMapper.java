package com.api.poke.repository.mappers;

import com.api.poke.model.Pokemon;
import com.api.poke.repository.entities.PokemonEntity;
import org.springframework.stereotype.Component;

@Component
public class PokemonEntityMapper {

    public PokemonEntity toEntity(Pokemon pokemon) {
        PokemonEntity entity = new PokemonEntity();
        entity.setId(pokemon.getId());
        entity.setName(pokemon.getName());
        entity.setExperience(pokemon.getExperience());
        entity.setEvolutionLevel(pokemon.getEvolutionLevel());
        entity.setEvolves(pokemon.isEvolves());
        //entity.setImage(pokemon.getImage());
        return entity;
    }


    public Pokemon toModel(PokemonEntity entity) {
        Pokemon pokemon = new Pokemon.Builder()
                .id_pokemon(entity.getId())
                .name(entity.getName())
                .experience(entity.getExperience())
                .evolutionLevel(entity.getEvolutionLevel())
                .evolves(entity.isEvolves())
              //  .image(entity.getImage())
                .build(); // Aquí se construye la instancia de Pokemon
        return pokemon;
    }
}
