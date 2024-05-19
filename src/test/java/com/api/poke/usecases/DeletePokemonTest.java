package com.api.poke.usecases;

import com.api.poke.exceptions.PokemonNotFoundException;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.usecases.DeletePokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class DeletePokemonTest {
    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private DeletePokemon deletePokemon;

    @Test
    public void testDeleteById_PokemonFound_DeletesPokemon() {
        // Arrange
        UUID id = UUID.randomUUID();
        PokemonEntity pokemonEntity = new PokemonEntity();
        when(pokemonRepository.findById(id)).thenReturn(Optional.of(pokemonEntity));

        // Act
        deletePokemon.deleteById(id);

        // Assert
        verify(pokemonRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteById_PokemonNotFound_ThrowsException() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(pokemonRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PokemonNotFoundException.class, () -> deletePokemon.deleteById(id));
        verify(pokemonRepository, never()).deleteById(id);
    }
}
