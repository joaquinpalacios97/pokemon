package com.api.poke.model;


import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Gym {

    UUID id;

    String name;

    PokemonType type;

    private List<Trainer> trainers;
}
