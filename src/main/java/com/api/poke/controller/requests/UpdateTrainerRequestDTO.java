package com.api.poke.controller.requests;

import com.api.poke.model.Gym;
import com.api.poke.repository.entities.PokemonEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class UpdateTrainerRequestDTO {

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotNull
    @Size(min = 3, max = 3, message = "Los entrenadores deben contener exactamente 3 pokemones")
    @Valid
    private List<PokemonEntity> pokemons;
}
