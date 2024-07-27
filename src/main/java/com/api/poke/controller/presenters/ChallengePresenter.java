package com.api.poke.controller.presenters;

import com.api.poke.controller.responses.ChallengeResponseDTO;
import com.api.poke.controller.responses.TrainerPokemonMovementResponseDTO;
import com.api.poke.controller.responses.TrainerPokemonResponseDTO;
import com.api.poke.controller.responses.TrainerResponseDTO;
import com.api.poke.model.Challenge;
import com.api.poke.service.ChallengeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ChallengePresenter {

    private static final org.slf4j.Logger logger = (Logger) LoggerFactory.getLogger(ChallengePresenter.class);


    private final TrainerPresenter trainerPresenter;
    private final ChallengeService challengeService;

    public ChallengeResponseDTO toResponse(Challenge challenge) {
        TrainerResponseDTO challenger = trainerPresenter.toResponse(challenge.getChallenger());

        logger.debug("Challenger en el presenterchallenger: {}", challenger);

        List<TrainerResponseDTO> enemies = challengeService.findEnemiesByChallengerId(challenge.getChallenger().getId())
                .stream()
                .map(trainer -> {
                    logger.debug("Processing enemy: {}", trainer);
                    return trainerPresenter.toResponse(trainer);
                })
                .collect(Collectors.toList());


        logger.debug("Enemies en el challengepresenter: {}", enemies);


        return ChallengeResponseDTO.builder()
                .challengeId(challenge.getId())
                .challenger(challenger)
                .enemies(enemies)
                .progress(challenge.getProgress())
                .build();
    }
}
