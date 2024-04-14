package com.api.poke.controller.requests;

import com.api.poke.model.PokemonType;
import com.api.poke.model.Trainer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true)
public class CreateGymRequestDTO {

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotNull(message = "Type cannot be null")
    PokemonType type;

    @NotEmpty(message = "List cannot be empty")
    List<Trainer> trainers;
}
