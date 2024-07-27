package com.api.poke.service;

import com.api.poke.controller.requests.CreateChallengeRequestDTO;
import com.api.poke.controller.requests.CreateTrainerRequestDTO;
import com.api.poke.controller.responses.TrainerResponseDTO;
import com.api.poke.model.Challenge;

import java.util.List;
import java.util.UUID;

public interface IChallengeService {
    Challenge createChallenge(UUID challengerId);
}
