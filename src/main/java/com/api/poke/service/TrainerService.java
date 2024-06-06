package com.api.poke.service;

import com.api.poke.controller.requests.CreateTrainerRequestDTO;
import com.api.poke.controller.requests.CreateTrainerPokemonsRequest;
import com.api.poke.controller.requests.UpdatePokemonTrainerRequest;
import com.api.poke.controller.requests.UpdateTrainerRequestDTO;
import com.api.poke.exceptions.PokemonNotFoundException;
import com.api.poke.exceptions.TrainerNotFoundException;
import com.api.poke.model.Pokemon;
import com.api.poke.model.Trainer;
import com.api.poke.repository.PokemonRepository;
import com.api.poke.repository.TrainerRepository;
import com.api.poke.repository.entities.*;
import com.api.poke.repository.MovementRepository;
import com.api.poke.repository.mappers.FullTrainerEntityMapper;
import com.api.poke.repository.mappers.GymEntityMapper;
import com.api.poke.repository.mappers.PokemonEntityMapper;
import com.api.poke.repository.mappers.TrainerEntityMapper;
import com.api.poke.usecases.GymFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrainerService implements ITrainerService {

    PokemonRepository pokemonRepository;
    TrainerRepository trainerRepository;
    TrainerEntityMapper trainerEntityMapper;
    GymEntityMapper gymEntityMapper;
    GymFinder gymFinder;
    MovementRepository movementRepository;
    PokemonEntityMapper pokemonEntityMapper;
    FullTrainerEntityMapper fullTrainerEntityMapper;

    @Override
    public List<Trainer> findAll() {
        return trainerRepository
                .findAll()
                .stream()
                .map(entity -> fullTrainerEntityMapper.toModel(entity))
                .collect(Collectors.toList());
    }

    public Trainer saveTrainer(CreateTrainerRequestDTO requestDTO) {
        Trainer trainer = Trainer.builder()
                .name(requestDTO.getName())
                .build();

        List<PokemonEntity> allPokemons = pokemonRepository.findAll();
        Collections.shuffle(allPokemons);
        List<PokemonEntity> randomPokemons = allPokemons.stream()
                .limit(3)
                .toList();

        TrainerEntity trainerEntity = trainerEntityMapper.toEntity(trainer);

        List<TrainerPokemonEntity> trainerPokemon = randomPokemons.stream()
                .map(pokemon -> {
                    List<MovementEntity> movementsByType = movementRepository.findAllByType(pokemon.getType());
                    Collections.shuffle(movementsByType);
                    List<MovementEntity> randomMovements = movementsByType.stream()
                            .limit(4)
                            .toList();

                    TrainerPokemonEntity trainerPokemonEntity = TrainerPokemonEntity.builder()
                            .trainer(trainerEntity)
                            .pokemon(pokemon)
                            .build();

                    List<TrainerPokemonMovementEntity> trainerPokemonMovementEntities = randomMovements.stream()
                            .map(movement -> TrainerPokemonMovementEntity.builder()
                                    .trainerPokemon(trainerPokemonEntity)
                                    .movement(movement)
                                    .build())
                            .toList();

                    trainerPokemonEntity.setTrainerPokemonMovements(trainerPokemonMovementEntities);

                    return trainerPokemonEntity;
                })
                .toList();

        trainerEntity.setPokeTrainers(trainerPokemon);

        TrainerEntity savedTrainerEntity = trainerRepository.save(trainerEntity);

        return  fullTrainerEntityMapper.toModel(savedTrainerEntity);

    }

    @Override
    public void deleteTrainer(UUID id) {
        Optional<TrainerEntity> trainerEntity = trainerRepository.findById(id);
        if (trainerEntity.isEmpty()) {
            throw new TrainerNotFoundException("Trainer not found for id " + id);
        }
        trainerRepository.deleteById(id);
    }

    @Override
    public Trainer findById(UUID id) {
        Optional<TrainerEntity> trainerEntity = trainerRepository.findById(id);
        if (trainerEntity.isEmpty()) {
            throw new TrainerNotFoundException("Trainer not found for id " + id);
        }
        return trainerEntityMapper.toModel(trainerEntity.get());
    }

    @Override
    public Trainer updateTrainer(UUID id, UpdateTrainerRequestDTO requestDTO) {
        Optional<TrainerEntity> trainerEntity = trainerRepository.findById(id);
        if (trainerEntity.isEmpty()) {
            throw new TrainerNotFoundException("Trainer not found for id " + id);
        }
        TrainerEntity trainer = trainerEntity.get();
        if (requestDTO.getName() != null) {
            trainer.setName(requestDTO.getName());
        }

        return trainerEntityMapper.toModel(trainerRepository.save(trainer));
    }

    @Override
    public Trainer updateTrainerPokemons(UUID trainerId, CreateTrainerPokemonsRequest requestDTO) {
        Optional<TrainerEntity> trainerEntityOptional = trainerRepository.findById(trainerId);
        if (trainerEntityOptional.isEmpty()) {
            throw new TrainerNotFoundException("Trainer not found for id " + trainerId);
        }
        TrainerEntity trainerEntity = trainerEntityOptional.get();

        //List<PokemonEntity> currentPokemons = trainerEntity.getPokemons();

        //currentPokemons.clear();

        List<UUID> pokemonIds = requestDTO.getIds();

        for (UUID pokemonId : pokemonIds) {
            Optional<PokemonEntity> pokemonEntityOptional = pokemonRepository.findById(pokemonId);

            if (pokemonEntityOptional.isEmpty()) {
                throw new PokemonNotFoundException("Pokemon not found or id " + pokemonId);
            }

            //currentPokemons.add(pokemonEntityOptional.get());
        }

        //trainerEntity.setPokemons(currentPokemons);
        return trainerEntityMapper.toModel(trainerRepository.save(trainerEntity));
    }


    @Override
    public Trainer updateTrainerPokemon(UUID trainerId, UpdatePokemonTrainerRequest requestDTO) {
        Optional<TrainerEntity> trainerEntityOptional = trainerRepository.findById(trainerId);
        if (trainerEntityOptional.isEmpty()) {
            throw new TrainerNotFoundException("Trainer not found for id " + trainerId);
        }
        TrainerEntity trainerEntity = trainerEntityOptional.get();

      //  List<PokemonEntity> currentPokemons = trainerEntity.getPokemons();

      //  currentPokemons.removeIf(pokemonEntity -> pokemonEntity.getId().equals(requestDTO.getOldPokemonId()));

        Optional<PokemonEntity> newPokemonOptional = pokemonRepository.findById(requestDTO.getNewPokemonId());
        if (newPokemonOptional.isEmpty()) {
            throw new PokemonNotFoundException("New Pokemon not found for id " + requestDTO.getNewPokemonId());
        }
      //  currentPokemons.add(newPokemonOptional.get());

       //trainerEntity.setPokemons(currentPokemons);
        return trainerEntityMapper.toModel(trainerRepository.save(trainerEntity));
    }

}
