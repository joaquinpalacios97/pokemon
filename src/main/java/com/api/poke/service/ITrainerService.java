package com.api.poke.service;

import com.api.poke.controller.requests.CreateTrainerRequestDTO;
import com.api.poke.controller.requests.CreateTrainerPokemonsRequest;
import com.api.poke.controller.requests.UpdatePokemonTrainerRequest;
import com.api.poke.controller.requests.UpdateTrainerRequestDTO;
import com.api.poke.model.Trainer;
import com.api.poke.model.TrainerType;


import java.util.List;
import java.util.UUID;

public interface ITrainerService {
     List<Trainer> findAll();
     Trainer saveTrainer (CreateTrainerRequestDTO requestDTO);
     void deleteTrainer (UUID id);
     Trainer findById(UUID id);

     Trainer updateTrainer(UUID id, UpdateTrainerRequestDTO requestDTO);

     Trainer updateTrainerPokemons (UUID id, CreateTrainerPokemonsRequest requestDTO);

     Trainer updateTrainerPokemon (UUID trainerId, UpdatePokemonTrainerRequest requestDTO);

     Trainer createAndSaveTrainer(String name, TrainerType type);
}
