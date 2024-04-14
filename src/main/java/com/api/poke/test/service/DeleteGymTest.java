package com.api.poke.test.service;

import com.api.poke.exceptions.GymNotFoundException;
import com.api.poke.repository.GymRepository;
import com.api.poke.repository.entities.GymEntity;
import com.api.poke.usecases.DeleteGym;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteGymTest {
    @Mock
    private GymRepository gymRepository;

    @InjectMocks
    private DeleteGym deleteGym;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteById_GymFound_DeletesGym() {
        // Arrange
        UUID id = UUID.randomUUID();
        GymEntity gymEntity = new GymEntity();
        when(gymRepository.findById(id)).thenReturn(Optional.of(gymEntity));

        // Act
        deleteGym.deleteById(id);

        // Assert
        verify(gymRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteById_GymNotFound_ThrowsException() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(gymRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(GymNotFoundException.class, () -> deleteGym.deleteById(id));
        verify(gymRepository, never()).deleteById(id);
    }
}
