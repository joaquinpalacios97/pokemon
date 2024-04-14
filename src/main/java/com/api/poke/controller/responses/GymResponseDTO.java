package com.api.poke.controller.responses;

import com.api.poke.model.PokemonType;
import com.api.poke.model.Trainer;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@FieldDefaults(makeFinal = true)
public class GymResponseDTO {
    String name;
    PokemonType type;
    List<Trainer>trainers;
}
