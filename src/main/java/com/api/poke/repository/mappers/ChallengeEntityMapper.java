package com.api.poke.repository.mappers;

import com.api.poke.model.Challenge;
import com.api.poke.repository.entities.ChallengeEntity;
import lombok.AllArgsConstructor;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ChallengeEntityMapper {

    private final FullTrainerEntityMapper trainerEntityMapper;

    public Challenge toModel(ChallengeEntity challengeEntity) {
        return Challenge.builder()
                .id(challengeEntity.getId())
                .status(challengeEntity.getStatus())
                .challenger(trainerEntityMapper.toModel(challengeEntity.getChallenger()))
                .enemy(trainerEntityMapper.toModel(challengeEntity.getEnemy()))
                .progress(challengeEntity.getProgress())
                .build();
    }

    public ChallengeEntity toEntity(Challenge challenge) {
        return ChallengeEntity.builder()
                .id(challenge.getId())
                .status(challenge.getStatus())
                .challenger(trainerEntityMapper.toEntity(challenge.getChallenger()))
                .enemy(trainerEntityMapper.toEntity(challenge.getEnemy()))
                .progress(challenge.getProgress())
                .build();
    }
}
