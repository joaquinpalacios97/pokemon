package com.api.poke.repository.entities;

import com.api.poke.model.TrainerType;
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
@Table(name = "Trainer")
public class TrainerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(columnDefinition = "VARCHAR(36)")
    private UUID id;

    private String name;

    private TrainerType type;

    @OneToMany(  cascade = CascadeType.ALL,fetch = FetchType.LAZY , mappedBy = "trainer")
    private List<TrainerPokemonEntity> pokeTrainers;

}