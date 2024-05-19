package com.api.poke.controller.requests;

import com.api.poke.model.PokemonType;
import com.api.poke.model.Trainer;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
@Getter
public class UpdateGymRequestDTO {

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotEmpty(message = "Type cannot be empty")
    PokemonType type;

    @NotEmpty(message = "List cannot be empty")
    List<UUID> trainersIds;
}
