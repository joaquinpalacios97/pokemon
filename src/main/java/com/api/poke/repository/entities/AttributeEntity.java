package com.api.poke.repository.entities;

import com.api.poke.model.PokemonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Attribute")
public class AttributeEntity {

    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int hp;
    private int attack;
    private int defense;
    private int sp_attack;
    private int sp_defense;
    private int speed;
}
