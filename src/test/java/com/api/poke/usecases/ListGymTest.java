package com.api.poke.usecases;

import com.api.poke.model.Gym;
import com.api.poke.repository.GymRepository;
import com.api.poke.repository.entities.GymEntity;
import com.api.poke.repository.mappers.GymEntityMapper;
import com.api.poke.usecases.ListGym;
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
public class ListGymTest {
    @Mock
    private GymRepository gymRepository;

    @Mock
    private GymEntityMapper gymEntityMapper;

    @InjectMocks
    private ListGym listGym;

    @Test
    public void testExecute_ReturnsListOfGyms() {
        // Arrange
        UUID id = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        GymEntity gymEntity1 = new GymEntity();
        gymEntity1.setId(id);
        GymEntity gymEntity2 = new GymEntity();
        gymEntity2.setId(id2);
        List<GymEntity> gymEntityList = Arrays.asList(gymEntity1, gymEntity2);

        Gym gym1 = new Gym();
        gym1.setId(id);
        Gym gym2 = new Gym();
        gym2.setId(id2);
        List<Gym> expectedGymList = Arrays.asList(gym1, gym2);

        when(gymRepository.findAll()).thenReturn(gymEntityList);
        when(gymEntityMapper.toModel(gymEntity1)).thenReturn(gym1);
        when(gymEntityMapper.toModel(gymEntity2)).thenReturn(gym2);

        // Act
        List<Gym> actualGymList = listGym.execute();

        // Assert
        assertEquals(expectedGymList.size(), actualGymList.size());
        assertEquals(expectedGymList, actualGymList);
    }
}
