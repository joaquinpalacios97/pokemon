package com.api.poke.mappers;
import com.api.poke.model.Gym;
import com.api.poke.model.PokemonType;
import com.api.poke.model.Trainer;
import com.api.poke.repository.entities.GymEntity;
import com.api.poke.repository.entities.TrainerEntity;
import com.api.poke.repository.mappers.TrainerEntityMapper;
import com.api.poke.repository.mappers.GymEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class GymMapperTest {

    @Mock
    private TrainerEntityMapper trainerEntityMapper;

    @InjectMocks
    private GymEntityMapper gymEntityMapper;

    @Test
    public void testToEntity() {
        UUID gymId = UUID.randomUUID();
        String gymName = "Water Gym";
        String gymType = "WATER";
        List<TrainerEntity> trainerEntities = Arrays.asList(new TrainerEntity(), new TrainerEntity());

        Gym gym =  Gym.builder()
                .id(gymId)
                .name(gymName)
                .type(PokemonType.valueOf(gymType))
                .trainers(Arrays.asList(new Trainer(), new Trainer()))
                .build();


        when(trainerEntityMapper.toEntity(gym.getTrainers().get(0))).thenReturn(new TrainerEntity());
        when(trainerEntityMapper.toEntity(gym.getTrainers().get(1))).thenReturn(new TrainerEntity());


        GymEntity gymEntity = gymEntityMapper.toEntity(gym);


        assertEquals(gymId, gymEntity.getId());
        assertEquals(gymName, gymEntity.getName());
        assertEquals(gymType, gymEntity.getType().toString());
        assertEquals(trainerEntities.size(), gymEntity.getTrainers().size());
    }

    @Test
    public void testToModel() {

        UUID gymId = UUID.randomUUID();
        String gymName = "Water Gym";
        String gymType = "WATER";
        List<Trainer> trainers = Arrays.asList(new Trainer(), new Trainer());

        GymEntity gymEntity = new GymEntity();
        gymEntity.setId(gymId);
        gymEntity.setName(gymName);
        gymEntity.setType(PokemonType.valueOf(gymType));
        gymEntity.setTrainers(Arrays.asList(new TrainerEntity(), new TrainerEntity()));


        when(trainerEntityMapper.toModel(gymEntity.getTrainers().get(0))).thenReturn(new Trainer());
        when(trainerEntityMapper.toModel(gymEntity.getTrainers().get(1))).thenReturn(new Trainer());


        Gym gym = gymEntityMapper.toModel(gymEntity);


        assertEquals(gymId, gym.getId());
        assertEquals(gymName, gym.getName());
        assertEquals(gymType, gym.getType().toString());
        assertEquals(trainers.size(), gym.getTrainers().size());
    }
}