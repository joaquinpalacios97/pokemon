package com.api.poke.model;

import com.api.poke.repository.entities.MovementEntity;
import com.api.poke.repository.entities.TrainerPokemonEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrainerPokemonMovement {

    private UUID id;

    @ManyToOne
    @JoinColumn(name = "trainer_pokemon_id", nullable = false)
    private TrainerPokemon trainerPokemon;

    @ManyToOne
    @JoinColumn(name = "movement_id", nullable = false)
    private Movement movement;
}
