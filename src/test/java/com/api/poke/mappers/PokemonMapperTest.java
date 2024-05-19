package com.api.poke.mappers;
import com.api.poke.repository.mappers.PokemonEntityMapper;
import com.api.poke.model.Pokemon;
import com.api.poke.repository.entities.PokemonEntity;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.extension.ExtendWith;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PokemonMapperTest {

    @Test
    public void testToEntity() {
        PokemonEntityMapper mapper = new PokemonEntityMapper(); // Crear una instancia del mapper

        UUID pokemonId = UUID.randomUUID();
        Pokemon pokemon = Pokemon.builder()
                .id(pokemonId)
                .name("Pikachu")
                .experience(100)
                .evolutionLevel(10)
                .evolves(true)
                .build();

        PokemonEntity expectedEntity = PokemonEntity.builder()
                .id(pokemonId)
                .name("Pikachu")
                .experience(100)
                .evolutionLevel(10)
                .evolves(true)
                .build();

        PokemonEntity resultEntity = mapper.toEntity(pokemon);
        assertEquals(expectedEntity, resultEntity);
    }

    @Test
    public void testToModel() {
        PokemonEntityMapper mapper = new PokemonEntityMapper(); // Crear una instancia del mapper

        UUID pokemonId = UUID.randomUUID();
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(pokemonId);
        pokemonEntity.setName("Pikachu");
        pokemonEntity.setExperience(100);
        pokemonEntity.setEvolutionLevel(10);
        pokemonEntity.setEvolves(true);

        Pokemon expectedPokemon = Pokemon.builder()
                .id(pokemonId)
                .name("Pikachu")
                .experience(100)
                .evolutionLevel(10)
                .evolves(true)
                .build();

        Pokemon resultPokemon = mapper.toModel(pokemonEntity);
        assertEquals(expectedPokemon, resultPokemon);
    }
}