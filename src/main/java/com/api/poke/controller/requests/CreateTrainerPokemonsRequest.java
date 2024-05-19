package com.api.poke.controller.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTrainerPokemonsRequest {

    @NotNull
    private List<UUID> ids;

}
