package com.api.poke.mappers;

import com.api.poke.repository.mappers.TrainerEntityMapper;
import com.api.poke.model.Trainer;
import com.api.poke.repository.entities.TrainerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TrainerMapperTest {

    @InjectMocks
    private TrainerEntityMapper trainerEntityMapper;

    @Test
    public void testToEntity() {
        UUID trainerId = UUID.randomUUID();
        String trainerName = "Ash";

        Trainer trainer = Trainer.builder()
                .id(trainerId)
                .name(trainerName)
                .build();

        TrainerEntity expectedEntity = TrainerEntity.builder()
                .id(trainerId)
                .name(trainerName)
                .build();

        TrainerEntity resultEntity = trainerEntityMapper.toEntity(trainer);

        assertEquals(expectedEntity, resultEntity);
    }

    @Test
    public void testToModel() {
        UUID trainerId = UUID.randomUUID();
        String trainerName = "Misty";

        TrainerEntity trainerEntity = TrainerEntity.builder()
                .id(trainerId)
                .name(trainerName)
                .build();

        Trainer expectedTrainer = Trainer.builder()
                .id(trainerId)
                .name(trainerName)
                .build();

        Trainer resultTrainer = trainerEntityMapper.toModel(trainerEntity);

        assertEquals(expectedTrainer, resultTrainer);
    }
}