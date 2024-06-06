package com.api.poke.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Trainer_Pokemon_Movement")
public class TrainerPokemonMovementEntity {
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "trainer_pokemon_id", nullable = false)
    private TrainerPokemonEntity trainerPokemon;

    @ManyToOne
    @JoinColumn(name = "movement_id", nullable = false)
    private MovementEntity movement;
}
