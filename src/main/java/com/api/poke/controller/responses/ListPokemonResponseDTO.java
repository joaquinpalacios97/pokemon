package com.api.poke.controller.responses;

import com.api.poke.model.Attribute;
import com.api.poke.model.PokemonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListPokemonResponseDTO {

    private UUID id;

    private String name;

    private PokemonType type;

    private int experience;

    private int evolutionLevel;

    private String imageBase64;

    private boolean evolves;
  //  private byte[] image;
  //  private String imageBase64;
    Attribute attributes;
}