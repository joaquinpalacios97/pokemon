package com.api.poke.repository.entities;

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
public class TrainerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(columnDefinition = "VARCHAR(36)")
    private UUID id;

    String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PokemonEntity> pokemons;

}