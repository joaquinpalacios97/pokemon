package com.api.poke.test.service;

import com.api.poke.exceptions.GymNotFoundException;
import com.api.poke.model.Gym;
import com.api.poke.repository.GymRepository;
import com.api.poke.repository.entities.GymEntity;
import com.api.poke.repository.mappers.GymEntityMapper;
import com.api.poke.usecases.FindGym;
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

public class FindByIdGymTest {
    @Mock
    private GymRepository gymRepository;

    @Mock
    private GymEntityMapper mapper;

    @InjectMocks
    private FindGym findGym;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById_ExistingId_ReturnsGym() {
        UUID id = UUID.randomUUID();
        GymEntity gymEntity = new GymEntity();
        gymEntity.setId(id);

        Gym expectedGym = new Gym();
        expectedGym.setId(id);

        when(gymRepository.findById(id)).thenReturn(Optional.of(gymEntity));
        when(mapper.toModel(gymEntity)).thenReturn(expectedGym);

        Gym result = findGym.findById(id);

        assertEquals(expectedGym, result);
    }

    @Test
    public void testFindById_NonExistingId_ThrowsGymNotFoundException() {
        UUID id = UUID.randomUUID();

        when(gymRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(GymNotFoundException.class, () -> {
            findGym.findById(id);
        });
    }
}
