package com.api.poke.controller.requests;


import com.api.poke.model.Pokemon;
import com.api.poke.repository.entities.PokemonEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor(force = true)
@FieldDefaults(makeFinal = true)
public class CreateTrainerRequestDTO {

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotNull
    @Size(min = 3, max = 3, message = "Los entrenadores deben contener exactamente 3 pokemones")
    @Valid
    private List<Pokemon> pokemons;

}