package com.api.poke.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class UpdatePokemonRequestDTO {
     String name;
     int experience;
     int evolutionLevel;
     boolean evolves;
     byte[] image;

}
