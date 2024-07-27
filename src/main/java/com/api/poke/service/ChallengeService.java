package com.api.poke.service;



import com.api.poke.model.*;
import com.api.poke.repository.ChallengeRepository;
import com.api.poke.repository.TrainerRepository;
import com.api.poke.repository.entities.ChallengeEntity;
import com.api.poke.repository.entities.TrainerEntity;
import com.api.poke.repository.mappers.FullTrainerEntityMapper;
import com.api.poke.repository.mappers.TrainerEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ChallengeService implements IChallengeService {


    private final TrainerService trainerService;
    private final ChallengeRepository challengeRepository;
    private final TrainerRepository trainerRepository;
    private final FullTrainerEntityMapper fullTrainerEntityMapper;
    private final TrainerEntityMapper trainerEntityMapper;

    @Transactional
    public Challenge createChallenge(UUID challengerId) {
        try {
            TrainerEntity challengerEntity = trainerRepository.findById(challengerId)
                    .orElseThrow(() -> new IllegalArgumentException("Challenger not found"));


            String enemyName1 = "enemy1";
            Trainer enemy1 = trainerService.createAndSaveTrainer(enemyName1, TrainerType.ENEMY);
            ChallengeEntity challengeEntity1 = ChallengeEntity.builder()
                    .progress(ChallengeProgress.IN_PROGRESS)
                    .challenger(challengerEntity)
                    .enemy(fullTrainerEntityMapper.toEntity(enemy1))
                    .build();
            challengeRepository.save(challengeEntity1);

            String enemyName2 = "enemy2";
            Trainer enemy2 = trainerService.createAndSaveTrainer(enemyName2, TrainerType.ENEMY);
            ChallengeEntity challengeEntity2 = ChallengeEntity.builder()
                    .progress(ChallengeProgress.NOT_STARTED)
                    .challenger(challengerEntity)
                    .enemy(fullTrainerEntityMapper.toEntity(enemy2))
                    .build();
            challengeRepository.save(challengeEntity2);

            String enemyName3 = "enemy3";
            Trainer enemy3 = trainerService.createAndSaveTrainer(enemyName3, TrainerType.ENEMY);

            ChallengeEntity challengeEntity3 = ChallengeEntity.builder()
                    .progress(ChallengeProgress.NOT_STARTED)
                    .challenger(challengerEntity)
                    .enemy(fullTrainerEntityMapper.toEntity(enemy3))
                    .build();
            challengeRepository.save(challengeEntity3);

            Trainer challenger = fullTrainerEntityMapper.toModel(challengerEntity);

            List<Trainer> enemyModels = List.of(
                    fullTrainerEntityMapper.toModel(challengeEntity1.getEnemy()),
                    fullTrainerEntityMapper.toModel(challengeEntity2.getEnemy()),
                    fullTrainerEntityMapper.toModel(challengeEntity3.getEnemy())
            );

            Challenge challenge = new Challenge(challenger, enemyModels, ChallengeProgress.IN_PROGRESS);

            return challenge;

           // return new Challenge(challenger, enemyModels, ChallengeProgress.IN_PROGRESS);
        } catch (Exception e) {
            throw e;
        }}
    @Transactional
    public void reportBattleResult(UUID challengerId, UUID enemyId, boolean won) {

        ChallengeEntity currentChallenge = challengeRepository.findByChallengerIdAndEnemyId(challengerId, enemyId)
                .orElseThrow(() -> new IllegalArgumentException("Challenge not found"));


        if (won) {
            currentChallenge.setStatus(Status.WON);
            currentChallenge.setProgress(ChallengeProgress.COMPLETED);
        } else {
            currentChallenge.setStatus(Status.LOST);
            currentChallenge.setProgress(ChallengeProgress.COMPLETED);
        }
        challengeRepository.save(currentChallenge);


        if (won) {
            List<ChallengeEntity> remainingChallenges = challengeRepository.findByChallengerIdAndStatus(challengerId, ChallengeProgress.NOT_STARTED);
            if (!remainingChallenges.isEmpty()) {
                ChallengeEntity nextChallenge = remainingChallenges.get(0);
                nextChallenge.setProgress(ChallengeProgress.IN_PROGRESS);
                challengeRepository.save(nextChallenge);
            }
        }


    }
    public List<Trainer> findEnemiesByChallengerId(UUID challengerId) {
        List<ChallengeEntity> challenges = challengeRepository.findByChallengerId(challengerId);
        return challenges.stream()
                .map(challenge -> fullTrainerEntityMapper.toModel(challenge.getEnemy()))
                .collect(Collectors.toList());
    }
}