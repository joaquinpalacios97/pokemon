package com.api.poke.model;

import com.api.poke.repository.entities.TrainerPokemonEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {

    private UUID id;

    String name;

    private List<Pokemon> pokemons;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY , mappedBy = "trainer")
    private List<TrainerPokemon> pokeTrainers;
}
