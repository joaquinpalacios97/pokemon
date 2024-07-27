package com.api.poke.model;

import com.api.poke.repository.entities.AttributeEntity;
import com.api.poke.repository.entities.MovementEntity;
import com.api.poke.repository.entities.TrainerPokemonEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

//ESTE YA ES EL TRANSFORMADO
@Setter
@Getter
@Builder
public class Pokemon {

    private UUID id;

    private String name;

    private PokemonType type;

    private int experience;

    private Integer evolutionLevel;

    private boolean evolves;

  //  private byte[] image;

    private String imageBase64;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attributes_id", referencedColumnName = "id")
    private Attribute attributes;


    @OneToMany(mappedBy = "pokemon")
    private List<TrainerPokemon> pokeTrainers;

}

