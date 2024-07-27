package com.api.poke.repository.entities;

import com.api.poke.model.Trainer;
import com.api.poke.model.TrainerPokemon;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;
@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Trainer_Pokemon")
public class TrainerPokemonEntity {
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private TrainerEntity trainer;

    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    private PokemonEntity pokemon;

    @OneToMany(mappedBy = "trainerPokemon", cascade = CascadeType.ALL )
    private List<TrainerPokemonMovementEntity> trainerPokemonMovements;

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