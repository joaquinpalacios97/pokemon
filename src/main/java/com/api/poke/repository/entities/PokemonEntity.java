package com.api.poke.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEntity {
    @SequenceGenerator(name="pokeEntity", allocationSize=1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokeEntity")
    @Column(name = "id_pokemon")
    private Long id;

    private String name;

    private int experience;

    private int evolutionLevel;

    private boolean evolves;

    /*@Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;*/

   // private String imageBase64;
}

