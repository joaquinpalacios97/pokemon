package com.api.poke.usecases;

import com.api.poke.controller.requests.CreateGymRequestDTO;
import com.api.poke.model.Gym;
import com.api.poke.model.Trainer;
import com.api.poke.model.PokemonType;
import com.api.poke.repository.GymRepository;
import com.api.poke.repository.entities.GymEntity;
import com.api.poke.repository.mappers.GymEntityMapper;
import com.api.poke.repository.mappers.TrainerEntityMapper;
import com.api.poke.usecases.CreateGym;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CreateGymTest {
    @Mock
    private GymRepository gymRepository;

    @Mock
    private GymEntityMapper gymEntityMapper;

    @Mock
    private TrainerEntityMapper trainerEntityMapper;

    @InjectMocks
    private CreateGym createGym;

    @Test
    public void testExecute_ValidRequestDTO_CreatesGym() {
        // Arrange
        CreateGymRequestDTO requestDTO = CreateGymRequestDTO.builder()
                .name("PokeGym")
                .type(PokemonType.WATER)
                .trainers(Arrays.asList(
                        Trainer.builder().name("Ash").build(),
                        Trainer.builder().name("Brock").build()
                ))
                .build();

        Gym gym = Gym.builder()
                .name(requestDTO.getName())
                .type(requestDTO.getType())
                .trainers(requestDTO.getTrainers())
                .build();

        GymEntity gymEntity = new GymEntity();

        when(gymEntityMapper.toEntity(any(Gym.class))).thenReturn(gymEntity);
        when(gymRepository.save(any(GymEntity.class))).thenReturn(gymEntity);
        when(gymEntityMapper.toModel(any(GymEntity.class))).thenReturn(gym);


        Gym createdGym = createGym.execute(requestDTO);

        assertNotNull(createdGym);
        assertEquals(gym.getName(), createdGym.getName());
        assertEquals(gym.getType(), createdGym.getType());
        assertEquals(gym.getTrainers(), createdGym.getTrainers());
    }
}