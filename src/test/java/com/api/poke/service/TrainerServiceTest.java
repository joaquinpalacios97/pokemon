package com.api.poke.service;

import com.api.poke.controller.requests.CreateTrainerPokemonsRequest;
import com.api.poke.controller.requests.CreateTrainerRequestDTO;
import com.api.poke.controller.requests.UpdatePokemonTrainerRequest;
import com.api.poke.exceptions.TrainerNotFoundException;
import com.api.poke.model.Trainer;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.TrainerRepository;
import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.entities.TrainerEntity;
import com.api.poke.repository.mappers.TrainerEntityMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainerServiceTest {

    // Mocks necesarios para las pruebas
    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private PokemonRepository pokemonRepository;

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
                .pokemons(Collections.emptyList())
                .build();

        Trainer trainer = Trainer.builder()
                .name(requestDTO.getName())
                .pokemons(requestDTO.getPokemons())
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

        List<Trainer> actualTrainers = trainerService.findAll();

        assertEquals(expectedTrainers.size(), actualTrainers.size());
        assertEquals(expectedTrainers, actualTrainers);
    }

    @Test
    public void testUpdateTrainerPokemon_ValidRequest_UpdatesPokemon() {
        // Arrange
        UUID trainerId = UUID.randomUUID();
        UUID oldPokemonId = UUID.randomUUID();
        UUID newPokemonId = UUID.randomUUID();
        UUID otherPokemonId1 = UUID.randomUUID();
        UUID otherPokemonId2 = UUID.randomUUID();

        UpdatePokemonTrainerRequest requestDTO = UpdatePokemonTrainerRequest.builder()
                .oldPokemonId(oldPokemonId)
                .newPokemonId(newPokemonId)
                .build();

        TrainerEntity trainerEntity = new TrainerEntity();
        PokemonEntity oldPokemon = new PokemonEntity();
        oldPokemon.setId(oldPokemonId);
        PokemonEntity otherPokemon1 = new PokemonEntity();
        otherPokemon1.setId(otherPokemonId1);
        PokemonEntity otherPokemon2 = new PokemonEntity();
        otherPokemon2.setId(otherPokemonId2);
        trainerEntity.setPokemons(new ArrayList<>(Arrays.asList(oldPokemon, otherPokemon1, otherPokemon2)));

        PokemonEntity newPokemon = new PokemonEntity();
        newPokemon.setId(newPokemonId);

        when(trainerRepository.findById(trainerId)).thenReturn(Optional.of(trainerEntity));
        when(pokemonRepository.findById(newPokemonId)).thenReturn(Optional.of(newPokemon));
        when(trainerRepository.save(any(TrainerEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        trainerService.updateTrainerPokemon(trainerId, requestDTO);

        // Assert
        verify(trainerRepository).save(trainerEntity);
        assertEquals(3, trainerEntity.getPokemons().size());

        // Verifica que el Pokémon antiguo fue reemplazado
        assertTrue(trainerEntity.getPokemons().stream().anyMatch(pokemon -> pokemon.getId().equals(newPokemonId)));

        // Verifica que los otros Pokémon no fueron modificados
        assertTrue(trainerEntity.getPokemons().stream().anyMatch(pokemon -> pokemon.getId().equals(otherPokemonId1)));
        assertTrue(trainerEntity.getPokemons().stream().anyMatch(pokemon -> pokemon.getId().equals(otherPokemonId2)));

        // Verifica que el Pokémon antiguo no está presente en la lista
        assertFalse(trainerEntity.getPokemons().stream().anyMatch(pokemon -> pokemon.getId().equals(oldPokemonId)));
    }


    @Test
    public void testUpdateTrainerPokemons_ValidRequest_UpdatesPokemons() {
        // Arrange
        UUID trainerId = UUID.randomUUID();
        List<UUID> newPokemonIds = new ArrayList<>();

        newPokemonIds.add(UUID.randomUUID());
        newPokemonIds.add(UUID.randomUUID());
        newPokemonIds.add(UUID.randomUUID());

        CreateTrainerPokemonsRequest requestDTO = CreateTrainerPokemonsRequest.builder()
                .ids(newPokemonIds)
                .build();

        TrainerEntity trainerEntity = new TrainerEntity();
        trainerEntity.setPokemons(new ArrayList<> (Arrays.asList(new PokemonEntity())));

        when(trainerRepository.findById(trainerId)).thenReturn(Optional.of(trainerEntity));
        when(pokemonRepository.findById(any(UUID.class))).thenReturn(Optional.of(new PokemonEntity()));

        // Act
        trainerService.updateTrainerPokemons(trainerId, requestDTO);

        // Assert
        // Verifica que los Pokémon se hayan actualizado correctamente
        verify(trainerRepository).save(trainerEntity);
        assertEquals(newPokemonIds.size(), trainerEntity.getPokemons().size());
    }

}