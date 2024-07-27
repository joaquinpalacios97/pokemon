package com.api.poke.usecases;

import com.api.poke.model.Pokemon;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.mappers.PokemonEntityMapper;
import com.api.poke.usecases.ListPokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ListPokemonTest {

    @Mock
    private PokemonRepository repository;

    @Mock
    private PokemonEntityMapper mapper;

    @InjectMocks
    private ListPokemon listPokemon;

    @Test
    public void testExecute_ReturnsListOfPokemons() {

        UUID id = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        PokemonEntity pokemonEntity1 = new PokemonEntity();
        pokemonEntity1.setId(id);
        PokemonEntity pokemonEntity2 = new PokemonEntity();
        pokemonEntity2.setId(id2);
        List<PokemonEntity> pokemonEntityList = Arrays.asList(pokemonEntity1, pokemonEntity2);

        Pokemon pokemon1 =  Pokemon.builder()
                .id(id)
                .build();
        Pokemon pokemon2 =  Pokemon.builder()
                .id(id2)
                .build();
        List<Pokemon> expectedPokemonList = Arrays.asList(pokemon1, pokemon2);

        when(repository.findAll()).thenReturn(pokemonEntityList);
        when(mapper.toModel(pokemonEntity1)).thenReturn(pokemon1);
        when(mapper.toModel(pokemonEntity2)).thenReturn(pokemon2);


        List<Pokemon> actualPokemonList = listPokemon.execute();


        assertEquals(expectedPokemonList.size(), actualPokemonList.size());
        assertEquals(expectedPokemonList, actualPokemonList);
    }
}
