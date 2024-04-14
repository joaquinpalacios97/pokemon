package com.api.poke.test.service;

import com.api.poke.controller.requests.UpdatePokemonRequestDTO;
import com.api.poke.exceptions.PokemonNotFoundException;
import com.api.poke.model.Pokemon;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.mappers.PokemonEntityMapper;
import com.api.poke.usecases.UpdatePokemon;
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

public class UpdatePokemonTest {
    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private PokemonEntityMapper pokemonEntityMapper;

    @InjectMocks
    private UpdatePokemon updatePokemon;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_ExistingPokemon_ReturnsUpdatedPokemon() {
        // Arrange
        UUID id = UUID.randomUUID();
        UpdatePokemonRequestDTO requestDTO = new UpdatePokemonRequestDTO("Pikachu", 100, 2, true);
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(id);
        pokemonEntity.setName("Charmander");
        pokemonEntity.setExperience(50);
        pokemonEntity.setEvolutionLevel(1);
        pokemonEntity.setEvolves(false);

        Pokemon updatedPokemon = new Pokemon.Builder()
                .id_pokemon(pokemonEntity.getId())
                .name(requestDTO.getName())
                .experience(requestDTO.getExperience())
                .evolutionLevel(requestDTO.getEvolutionLevel())
                .evolves(requestDTO.isEvolves())
                .build();

        when(pokemonRepository.findById(id)).thenReturn(Optional.of(pokemonEntity));
        when(pokemonRepository.save(pokemonEntity)).thenReturn(pokemonEntity);
        when(pokemonEntityMapper.toModel(pokemonEntity)).thenReturn(updatedPokemon);

        // Act
        Pokemon result = updatePokemon.execute(id, requestDTO);

        // Assert
        assertEquals(updatedPokemon, result);
        assertEquals(requestDTO.getName(), result.getName());
        assertEquals(requestDTO.getExperience(), result.getExperience());
        assertEquals(requestDTO.getEvolutionLevel(), result.getEvolutionLevel());
        assertEquals(requestDTO.isEvolves(), result.isEvolves());
    }

    @Test
    public void testExecute_NonExistingPokemon_ThrowsException() {
        // Arrange
        UUID id = UUID.randomUUID();
        UpdatePokemonRequestDTO requestDTO = new UpdatePokemonRequestDTO("Pikachu", 100, 2, true);

        when(pokemonRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PokemonNotFoundException.class, () -> {
            updatePokemon.execute(id, requestDTO);
        });
    }
}
