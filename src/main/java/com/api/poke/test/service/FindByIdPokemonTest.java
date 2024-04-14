package com.api.poke.test.service;

import com.api.poke.exceptions.PokemonNotFoundException;
import com.api.poke.model.Pokemon;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.mappers.PokemonEntityMapper;
import com.api.poke.usecases.FindPokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class FindByIdPokemonTest {
    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private PokemonEntityMapper mapper;

    @InjectMocks
    private FindPokemon findPokemon;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById_ExistingId_ReturnsPokemon() {
        UUID id = UUID.randomUUID();
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(id);
        Pokemon expectedPokemon = new Pokemon.Builder()
                .id_pokemon(UUID.randomUUID())
                .build();


        when(pokemonRepository.findById(id)).thenReturn(Optional.of(pokemonEntity));
        when(mapper.toModel(pokemonEntity)).thenReturn(expectedPokemon);

        Pokemon result = findPokemon.findById(id);

        assertEquals(expectedPokemon, result);
    }

    @Test
    public void testFindById_NonExistingId_ThrowsPokemonNotFoundException() {
        UUID id = UUID.randomUUID();

        when(pokemonRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PokemonNotFoundException.class, () -> {
            findPokemon.findById(id);
        });
    }
}
