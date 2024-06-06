package com.api.poke.repository.entities;

import com.api.poke.model.PokemonType;
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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "Pokemon")
public class PokemonEntity {
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  UUID id;

    private String name;

    private PokemonType type;

    private int experience;

    private Integer evolutionLevel;

    private boolean evolves;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attributes_id", referencedColumnName = "id")
    private AttributeEntity attributes;


    @OneToMany(mappedBy = "pokemon")
    private List<TrainerPokemonEntity> pokeTrainers;

    /*@Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;*/

   // private String imageBase64;
}