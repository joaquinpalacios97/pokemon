package com.api.poke.controller.responses;

import com.api.poke.model.PokemonType;
import com.api.poke.model.Trainer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ListGymResponseDTO {
    String name;
    PokemonType type;
    List<Trainer>trainers;
}
