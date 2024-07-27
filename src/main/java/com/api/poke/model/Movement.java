package com.api.poke.model;

import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Movement {

    private UUID id;

    private String name;

    private PokemonType type;

    private int power;

    private int accuracy;

    private int pp;

}
