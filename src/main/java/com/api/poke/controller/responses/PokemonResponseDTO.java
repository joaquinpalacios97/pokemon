package com.api.poke.controller.responses;

import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(makeFinal = true)
public class PokemonResponseDTO {
//ESTO VA PARA EL FRONT
    String id;
    String name;
}