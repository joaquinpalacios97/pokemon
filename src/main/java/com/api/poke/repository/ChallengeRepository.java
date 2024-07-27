package com.api.poke.repository;

import com.api.poke.model.ChallengeProgress;
import com.api.poke.model.Status;
import com.api.poke.repository.entities.ChallengeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeEntity, UUID> {
    Optional<ChallengeEntity> findByChallengerIdAndEnemyId(UUID challengerId, UUID enemyId);
    List<ChallengeEntity> findByChallengerIdAndStatus(UUID challengerId, ChallengeProgress status);
    List<ChallengeEntity> findByChallengerId(UUID challengerId);
}
