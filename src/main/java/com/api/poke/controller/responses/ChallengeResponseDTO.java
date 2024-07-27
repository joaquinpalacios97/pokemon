package com.api.poke.controller.responses;

import com.api.poke.model.ChallengeProgress;
import com.api.poke.model.Status;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@FieldDefaults(makeFinal = true)
public class ChallengeResponseDTO {
    private UUID challengeId;
    private TrainerResponseDTO challenger;
    private List<TrainerResponseDTO> enemies;
    private ChallengeProgress progress;
}
