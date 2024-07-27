package com.api.poke.model;

import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.entities.TrainerEntity;
import com.api.poke.repository.entities.TrainerPokemonMovementEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TrainerPokemon {

    private UUID id;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    @OneToMany(mappedBy = "trainerPokemon")
    private List<TrainerPokemonMovement> trainerPokemonMovements;

    private int currentHp;

    @PostLoad
    @PostPersist
    public void initializeCurrentHp() {
        if (currentHp == 0) {
            this.currentHp = pokemon.getAttributes().getHp();
        }
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

}
