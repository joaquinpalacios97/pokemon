package com.api.poke.controller.responses;

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
public class ListMovementResponseDTO {

    private UUID id;

    private String name;

    private PokemonType type;

    private int power;

    private int accuracy;

    private int pp;
}
