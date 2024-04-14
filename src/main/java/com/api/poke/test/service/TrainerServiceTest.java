package com.api.poke.test.service;

import com.api.poke.controller.requests.CreateTrainerRequestDTO;
import com.api.poke.exceptions.TrainerNotFoundException;
import com.api.poke.model.Trainer;
import com.api.poke.repository.TrainerRepository;
import com.api.poke.repository.entities.TrainerEntity;
import com.api.poke.repository.mappers.TrainerEntityMapper;
import com.api.poke.usecases.GymFinder;
import com.api.poke.service.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainerServiceTest {

    // Mocks necesarios para las pruebas
    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private TrainerEntityMapper trainerEntityMapper;


    // Objeto a ser probado, en el cual se inyectan los mocks
    @InjectMocks
    private TrainerService trainerService;

    // Configuración antes de cada prueba
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba para verificar que findById() devuelve un Trainer existente
    @Test
    public void testFindById_ExistingId_ReturnsTrainer() {
        UUID id = UUID.randomUUID();
        TrainerEntity trainerEntity = new TrainerEntity();
        trainerEntity.setId(id);
        Trainer trainer = new Trainer();
        trainer.setId(id);

        // Configuración de comportamiento del mock
        when(trainerRepository.findById(id)).thenReturn(Optional.of(trainerEntity));
        when(trainerEntityMapper.toModel(trainerEntity)).thenReturn(trainer);

        // Llamada al método a probar
        Trainer result = trainerService.findById(id);

        // Verificación de resultados
        assertEquals(trainer, result);
    }

    // Prueba para verificar que findById() lanza una excepción cuando el ID no existe
    @Test
    public void testFindById_NonExistingId_ThrowsException() {
        UUID id = UUID.randomUUID();

        // Configuración de comportamiento del mock
        when(trainerRepository.findById(id)).thenReturn(Optional.empty());

        // Verificación de que se lanza una excepción
        assertThrows(TrainerNotFoundException.class, () -> trainerService.findById(id));
    }

    @Test
    public void testSaveTrainer_ValidRequestDTO_SavesTrainer() {
        // Arrange
        CreateTrainerRequestDTO requestDTO = CreateTrainerRequestDTO.builder()
                .name("Ash")
                .build();


        Trainer trainer = Trainer.builder()
                .name(requestDTO.getName())
                .build();
        TrainerEntity trainerEntity = new TrainerEntity();

        when(trainerEntityMapper.toEntity(any(Trainer.class))).thenReturn(trainerEntity);
        when(trainerEntityMapper.toModel(any(TrainerEntity.class))).thenReturn(trainer);
        when(trainerRepository.save(any(TrainerEntity.class))).thenReturn(trainerEntity);

        // Act
        Trainer savedTrainer = trainerService.saveTrainer(requestDTO);

        // Assert
        assertNotNull(savedTrainer);
        assertEquals(trainer.getName(), savedTrainer.getName());
    }

    @Test
    public void testDeleteTrainer_ExistingId_DeletesTrainer() {
        // Arrange
        UUID id = UUID.randomUUID();
        TrainerEntity trainerEntity = new TrainerEntity();
        when(trainerRepository.findById(id)).thenReturn(Optional.of(trainerEntity));

        // Act
        trainerService.deleteTrainer(id);

        // Assert
        verify(trainerRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteTrainer_NonExistingId_ThrowsTrainerNotFoundException() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(trainerRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TrainerNotFoundException.class, () -> {
            trainerService.deleteTrainer(id);
        });
    }

    @Test
    public void testListTrainers_ReturnsListOfTrainers() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();


        TrainerEntity trainerEntity1 = new TrainerEntity();
        trainerEntity1.setId(id1);
        TrainerEntity trainerEntity2 = new TrainerEntity();
        trainerEntity2.setId(id2);

        // Simular la respuesta del repositorio con las entidades creadas
        List<TrainerEntity> trainerEntitiesList = Arrays.asList(trainerEntity1, trainerEntity2);


        // Convertir las entidades de entrenador a modelos de entrenador
        Trainer trainer1 = new Trainer();
        trainer1.setId(id1);
        Trainer trainer2 = new Trainer();
        trainer2.setId(id2);
        List<Trainer> expectedTrainers = Arrays.asList(trainer1, trainer2);

        when(trainerRepository.findAll()).thenReturn(trainerEntitiesList);
        when(trainerEntityMapper.toModel(trainerEntity1)).thenReturn(trainer1);
        when(trainerEntityMapper.toModel(trainerEntity2)).thenReturn(trainer2);
        // Act

        List<Trainer> actualTrainers = trainerService.findAll();

        // Assert
        assertEquals(expectedTrainers.size(), actualTrainers.size());
        assertEquals(expectedTrainers, actualTrainers);
    }



}