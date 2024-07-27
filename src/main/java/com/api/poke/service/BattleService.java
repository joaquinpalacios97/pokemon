package com.api.poke.service;

import com.api.poke.model.Trainer;
import com.api.poke.model.TrainerPokemonMovement;
import com.api.poke.repository.entities.MovementEntity;
import com.api.poke.model.TrainerPokemon;

import com.api.poke.repository.mappers.MovementEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Random;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BattleService {


    private final MovementEntityMapper movementEntityMapper;


    public enum BattleResult {
        CHALLENGER_WON,
        ENEMY_WON,
        DRAW,
        IN_PROGRESS
    }

    public BattleResult battleTurn(Trainer challenger, Trainer enemy, UUID selectedMoveId) {
        TrainerPokemon challengerPokemon = getNextAlivePokemon(challenger);
        TrainerPokemon enemyPokemon = getNextAlivePokemon(enemy);

        MovementEntity challengerMove = selectMoveById(challengerPokemon, selectedMoveId);
        MovementEntity enemyMove = selectRandomMove(enemyPokemon);

        applyMove(challengerMove, enemyPokemon);
        if (!enemyPokemon.isAlive()) {
            if (!enemyHasAlivePokemons(enemy)) {
                return BattleResult.CHALLENGER_WON;
            }
            return BattleResult.IN_PROGRESS;
        }

        applyMove(enemyMove, challengerPokemon);
        if (!challengerPokemon.isAlive() && !challengerHasAlivePokemons(challenger)) {
            return BattleResult.ENEMY_WON;
        }

        return BattleResult.IN_PROGRESS;
    }

    private boolean challengerHasAlivePokemons(Trainer trainer) {
        return trainer.getPokeTrainers().stream().anyMatch(TrainerPokemon::isAlive);
    }

    private boolean enemyHasAlivePokemons(Trainer trainer) {
        return trainer.getPokeTrainers().stream().anyMatch(TrainerPokemon::isAlive);
    }

    private TrainerPokemon getNextAlivePokemon(Trainer trainer) {
        return trainer.getPokeTrainers().stream().filter(TrainerPokemon::isAlive).findFirst().orElseThrow(() -> new IllegalStateException("No alive Pokémon found for trainer: " + trainer.getId()));
    }

    private MovementEntity selectRandomMove(TrainerPokemon pokemon) {
        if (pokemon == null) {
            throw new IllegalArgumentException("TrainerPokemon is null");
        }
        List<TrainerPokemonMovement> moves = pokemon.getTrainerPokemonMovements();
        Random random = new Random();
        TrainerPokemonMovement randomMovement = moves.get(random.nextInt(moves.size()));
        return movementEntityMapper.toEntity(randomMovement.getMovement());
    }

    private MovementEntity selectMoveById(TrainerPokemon pokemon, UUID moveId) {
        return pokemon.getTrainerPokemonMovements().stream()
                .filter(m -> m.getMovement().getId().equals(moveId))
                .findFirst()
                .map(m -> movementEntityMapper.toEntity(m.getMovement()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid move ID"));
    }

    private void applyMove(MovementEntity move, TrainerPokemon targetPokemon) {
        int newHp = targetPokemon.getCurrentHp() - move.getPower();
        targetPokemon.setCurrentHp(Math.max(newHp, 0));
    }}


