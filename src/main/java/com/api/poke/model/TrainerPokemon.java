package com.api.poke.model;

import com.api.poke.repository.entities.PokemonEntity;
import com.api.poke.repository.entities.TrainerEntity;
import com.api.poke.repository.entities.TrainerPokemonMovementEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
}
