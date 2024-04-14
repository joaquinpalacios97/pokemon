package com.api.poke.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEntity {
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  UUID id;

    private String name;

    private int experience;

    private int evolutionLevel;

    private boolean evolves;

    /*@Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;*/

   // private String imageBase64;
}