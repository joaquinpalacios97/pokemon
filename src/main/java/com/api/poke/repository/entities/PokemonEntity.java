package com.api.poke.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_pokemon")
    private Long id_pokemon;

    private String name;

    private int experience;

    private int evolutionLevel;

    private boolean evolves;

    /*@Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;*/

   // private String imageBase64;
}

