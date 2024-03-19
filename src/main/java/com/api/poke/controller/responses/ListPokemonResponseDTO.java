package com.api.poke.controller.responses;

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
    private int experience;
    private int evolutionLevel;
    private boolean evolves;
  //  private byte[] image;
  //  private String imageBase64;
}