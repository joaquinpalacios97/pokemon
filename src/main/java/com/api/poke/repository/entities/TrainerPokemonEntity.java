package com.api.poke.repository.entities;

import com.api.poke.model.Trainer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;
@Data
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

    @OneToMany(mappedBy = "trainerPokemon", cascade = CascadeType.ALL)
    private List<TrainerPokemonMovementEntity> trainerPokemonMovements;
}
