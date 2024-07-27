package com.api.poke.controller.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@FieldDefaults(makeFinal = true)
@Getter
public class ReportBattleResultRequestDTO {
    @NotNull(message = "challengerId no puede ser nulo")
    private UUID challengerId;

    @NotNull(message = "enemyId no puede ser nulo")
    private UUID enemyId;

    private boolean won;
}
