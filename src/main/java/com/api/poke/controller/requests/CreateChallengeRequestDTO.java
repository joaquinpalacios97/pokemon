package com.api.poke.controller.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@FieldDefaults(makeFinal = true)
@Getter
public class CreateChallengeRequestDTO {

    @NotNull(message = "id no puede no existirrr")
    private UUID challengerId;
    // private TrainerResponseDTO challenger;
   // private List<TrainerResponseDTO> enemies;

}
